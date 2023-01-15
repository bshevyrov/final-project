package ua.com.company.view.controller.user;

import com.mysql.cj.util.StringUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.company.facade.ImageFacade;
import ua.com.company.facade.PersonAddressFacade;
import ua.com.company.facade.PersonFacade;
import ua.com.company.view.dto.ImageDTO;
import ua.com.company.view.dto.PersonAddressDTO;
import ua.com.company.view.dto.PersonDTO;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDetailsUpdateController extends HttpServlet {
    private PersonFacade personFacade;
    private PersonAddressFacade personAddressFacade;
    private ImageFacade imageFacade;
    private PersonDTO personDTO;
    private Set<String> errorFields;

    private final Logger log = LogManager.getLogger(UserDetailsUpdateController.class);

    @Override
    public void init() throws ServletException {
        personFacade = (PersonFacade) getServletContext().getAttribute("personFacade");
        personAddressFacade = (PersonAddressFacade) getServletContext().getAttribute("personAddressFacade");
        imageFacade = (ImageFacade) getServletContext().getAttribute("imageFacade");
        errorFields = new HashSet<>();

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/jsp/user/user-details-update.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("UserDetailsUpdateController error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int personId = ((PersonDTO) request.getSession().getAttribute("loggedPerson")).getId();
        personDTO = personFacade.findById(personId);
        request.setAttribute("person", personDTO);
        request.setAttribute("errorFields", errorFields);

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        errorFields.clear();
        PersonDTO newPersonDTO = new PersonDTO();
        int id = personDTO.getId();
        newPersonDTO.setId(id);
        String newUsername = request.getParameter("username");
        String newEmail = request.getParameter("email");
        String avatarPath = request.getParameter("avatarPath");
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setName(id + " avatar");
        imageDTO.setPath(avatarPath);
        newPersonDTO.setAvatar(imageDTO);

        newPersonDTO.setUsername(newUsername);
        newPersonDTO.setEmail(newEmail);

        if (!samePersonData(newPersonDTO)) {
            if (personFacade.isExistByEmail(newEmail)) {
                request.setAttribute("emailExist", true);
                request.setAttribute("person", personDTO);
                request.setAttribute("errorFields", errorFields);
                processRequest(request, response);
                return;
            }
            if (personFacade.isExistByUsername(newUsername)) {
                request.setAttribute("usernameExist", true);
                request.setAttribute("person", personDTO);
                request.setAttribute("errorFields", errorFields);
                processRequest(request, response);
                return;
            }
            processRequest(request, response);
            if (!validUserData(newPersonDTO)) {
                request.setAttribute("person", newPersonDTO);
                request.setAttribute("errorFields", errorFields);
                processRequest(request, response);
                return;
            }
            personFacade.update(newPersonDTO);
        }

        PersonAddressDTO newPersonAddressDTO = getPersonAddressDTO(request);
        newPersonDTO.setPersonAddressDTO(newPersonAddressDTO);
        errorFields.clear();
        if (!samePersonAddressData(newPersonAddressDTO)) {
            if (!validUserAddressData(newPersonAddressDTO)) {
                request.setAttribute("person", newPersonDTO);
                request.setAttribute("errorFields", errorFields);
                processRequest(request, response);
                return;
            }
            if (personDTO.getPersonAddressDTO().getId() != 0) {
                personAddressFacade.update(newPersonAddressDTO);
            } else {
                personAddressFacade.create(newPersonAddressDTO);
            }
        }

/*        if (personDTO.getPersonAddressDTO() != null) {
            if (!personDTO.getPersonAddressDTO().equals(newPersonAddressDTO)) {
                newPersonAddressDTO.setId(personDTO.getPersonAddressDTO().getId());
                personAddressFacade.update(newPersonAddressDTO);
            }
        } else {
            personAddressFacade.create(newPersonAddressDTO);
        }*/

        response.sendRedirect("/user/details");
}

    private boolean validUserAddressData(PersonAddressDTO newPersonAddressDTO) {
       
        stringValidation("city", newPersonAddressDTO.getCity());
        stringValidation("country", newPersonAddressDTO.getCountry());
        phoneValidation("phone", newPersonAddressDTO.getPhone());
        stringValidation("firstName", newPersonAddressDTO.getFirstName());
        stringValidation("lastName", newPersonAddressDTO.getLastName());
        numberValidation("postalCode", newPersonAddressDTO.getPostalCode());
        stringValidation("address", newPersonAddressDTO.getAddress());
        return errorFields.isEmpty();

    }

    private void phoneValidation(String fieldName, String value) {
        if (StringUtils.isNullOrEmpty(value)
                || StringUtils.isEmptyOrWhitespaceOnly(value)
                || !StringUtils.isStrictlyNumeric(value)) {
            errorFields.add(fieldName);
        }
    }

    private boolean validUserData(PersonDTO newPersonDTO) {
        stringValidation("username", newPersonDTO.getUsername());
        emailValidation("email", newPersonDTO.getEmail());
        return errorFields.isEmpty();
    }

    private void numberValidation(String fieldName, double value) {
        if (value <= 0) {
            errorFields.add(fieldName);
        }
    }

    private void emailValidation(String fieldName, String emailAddress) {
        Pattern regexPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = regexPattern.matcher(emailAddress);
        if (m.matches()) {
            errorFields.add(fieldName);
        }
    }

    private void stringValidation(String fieldName, String value) {
        if (StringUtils.isNullOrEmpty(value)
                || StringUtils.isEmptyOrWhitespaceOnly(value)
                || StringUtils.isStrictlyNumeric(value)) {
            errorFields.add(fieldName);
        }
    }

    private boolean samePersonAddressData(PersonAddressDTO newPersonAddressDTO) {
        PersonAddressDTO currentPersonAddressDTO = personDTO.getPersonAddressDTO();
        return newPersonAddressDTO.getAddress().equals(currentPersonAddressDTO.getAddress())
                && newPersonAddressDTO.getCity().equals(currentPersonAddressDTO.getCity())
                && newPersonAddressDTO.getCountry().equals(currentPersonAddressDTO.getCountry())
                && newPersonAddressDTO.getFirstName().equals(currentPersonAddressDTO.getFirstName())
                && newPersonAddressDTO.getLastName().equals(currentPersonAddressDTO.getLastName())
                && newPersonAddressDTO.getPhone().equals(currentPersonAddressDTO.getPhone())
                && newPersonAddressDTO.getPostalCode() == (currentPersonAddressDTO.getPostalCode());
    }

    private boolean samePersonData(PersonDTO newPersonDTO) {
        return newPersonDTO.getAvatar().getPath().equals(personDTO.getAvatar().getPath())
                && newPersonDTO.getEmail().equals(personDTO.getEmail())
                && newPersonDTO.getUsername().equals(personDTO.getUsername());
    }

    private PersonAddressDTO getPersonAddressDTO(HttpServletRequest request) {
        PersonAddressDTO personAddressDTO = new PersonAddressDTO();
        personAddressDTO.setFirstName(request.getParameter("firstName"));
        personAddressDTO.setLastName(request.getParameter("lastName"));
        personAddressDTO.setAddress(request.getParameter("address"));
        personAddressDTO.setCity(request.getParameter("city"));
        personAddressDTO.setCountry(request.getParameter("country"));
        personAddressDTO.setPhone(request.getParameter("phone"));
        personAddressDTO.setPostalCode(Integer.parseInt(request.getParameter("postalCode")));
        personAddressDTO.setPersonId(personDTO.getId());
        return personAddressDTO;
    }
}
