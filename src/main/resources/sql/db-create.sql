-- MySQL Script generated by MySQL Workbench
-- пт, 09-вер-2022 00:11:06 +0300
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
---------------------------------------------------
-- Schema final_project
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `final_project`;

-- -----------------------------------------------------
-- Schema final_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `final_project`;
USE `final_project`;

-- -----------------------------------------------------
-- Table `final_project`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`role`;

CREATE TABLE IF NOT EXISTS `final_project`.`role`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `description_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`person_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`person_status`;

CREATE TABLE IF NOT EXISTS `final_project`.`person_status`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`person`;

CREATE TABLE IF NOT EXISTS `final_project`.`person`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `email`       VARCHAR(36)  NOT NULL,
    `create_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `password`    VARCHAR(256) NOT NULL,
    `role_id`     INT          NOT NULL DEFAULT 1,
    `status_id`   INT          NOT NULL DEFAULT 1,
    `username`    VARCHAR(45)  NOT NULL,
    `funds`       DOUBLE                DEFAULT (0.0),
    `image_id`    INT,
    PRIMARY KEY (`id`),
    UNIQUE INDEX (`email` ASC) VISIBLE,
    FULLTEXT INDEX `idx_customer_email` (`email`) VISIBLE,
    INDEX `fk_person_role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_person_status1_idx` (`status_id` ASC) VISIBLE,
    CONSTRAINT `fk_person_role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `final_project`.`role` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE,
    CONSTRAINT `fk_person_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `final_project`.`person_status` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`publication`;

CREATE TABLE IF NOT EXISTS `final_project`.`publication`
(
    `id`          INT                    NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(2048)          NOT NULL,
    `create_date` TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date` TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `description` VARCHAR(4096)          NULL     DEFAULT NULL,
    `price`       DECIMAL(9, 2) UNSIGNED NOT NULL,
    `image_id`    INT,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`topic`;

CREATE TABLE IF NOT EXISTS `final_project`.`topic`
(
    `id`    INT          NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `final_project`.`publication_has_topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`publication_has_topic`;

CREATE TABLE IF NOT EXISTS `final_project`.`publication_has_topic`
(
    `publication_id` INT NOT NULL,
    `topic_id`       INT NOT NULL,
    PRIMARY KEY (`publication_id`, `topic_id`),
    INDEX `fk_publication_has_topic_topic1_idx` (`topic_id` ASC) VISIBLE,
    INDEX `fk_publication_has_topic_publication_idx` (`publication_id` ASC) VISIBLE,
    CONSTRAINT `fk_publication_has_topic_publication`
        FOREIGN KEY (`publication_id`)
            REFERENCES `final_project`.`publication` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_publication_has_topic_topic1`
        FOREIGN KEY (`topic_id`)
            REFERENCES `final_project`.`topic` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `final_project`.`person_has_publication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`person_has_publication`;

CREATE TABLE IF NOT EXISTS `final_project`.`person_has_publication`
(
    `person_id`      INT         NOT NULL,
    `publication_id` INT         NOT NULL,
    `comment`        VARCHAR(45) NULL DEFAULT NULL,
    PRIMARY KEY (`person_id`, `publication_id`),
    INDEX `fk_person_has_publication_publication1_idx` (`publication_id` ASC) VISIBLE,
    INDEX `fk_person_has_publication_person1_idx` (`person_id` ASC) VISIBLE,
    CONSTRAINT `fk_person_has_publication_person1`
        FOREIGN KEY (`person_id`)
            REFERENCES `final_project`.`person` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_person_has_publication_publication1`
        FOREIGN KEY (`publication_id`)
            REFERENCES `final_project`.`publication` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `final_project`.`person_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`person_details`;

CREATE TABLE IF NOT EXISTS `final_project`.`person_details`
(
    `person_id`  INT                    NOT NULL,
    `funds`      DECIMAL(9, 2) UNSIGNED NOT NULL DEFAULT 0.00,
    `first_name` VARCHAR(45)            NOT NULL,
    `last_name`  VARCHAR(45)            NOT NULL,
    PRIMARY KEY (`person_id`),
    CONSTRAINT `fk_person_details_person1`
        FOREIGN KEY (`person_id`)
            REFERENCES `final_project`.`person` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`publication_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`publication_details`;

CREATE TABLE IF NOT EXISTS `final_project`.`publication_details`
(
    `publication_id` INT         NOT NULL,
    `cover_name`     VARCHAR(45) NOT NULL,
    `cover_path`     VARCHAR(45) NOT NULL,
    PRIMARY KEY (`publication_id`),
    CONSTRAINT `fk_publication_details_publication1`
        FOREIGN KEY (`publication_id`)
            REFERENCES `final_project`.`publication` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `final_project`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `final_project`.`image`;

CREATE TABLE IF NOT EXISTS `final_project`.`image`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,

    `name`        VARCHAR(256) NOT NULL,
    `path`        VARCHAR(256) NOT NULL,
    `create_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


INSERT INTO person_status
VALUES (1, 'ENABLED'),
       (2, 'DISABLED');
INSERT INTO role
VALUES (1, 'ROLE_CUSTOMER'),
       (2, 'ROLE_ADMIN');
INSERT INTO person
VALUES (1, 'nefor89@gmail.com', now(), now(), '65535:ee2371f001922a7be621d1f1598465f6:dd8ee816129b683221aeeac1ffa67682',
        1, 1, 'd9i', 999.999,null);
INSERT INTO person
VALUES (2, 'bshevyrov@gmail.com', '2022-10-05 16:36:13', '2022-10-05 16:36:13',
        '65535:ee2371f001922a7be621d1f1598465f6:dd8ee816129b683221aeeac1ffa67682', 2, 1, 'BOSS', DEFAULT,null);


INSERT INTO `publication`
VALUES (1, 'Jealousy, Vol. 1', '2022-09-22 13:23:58', '2022-09-22 13:23:58',
        ' The prequel to the popular yakuza rom-com Fourth Generation Head: Tatsuyuki Oyamato!\r\n\r\nA yakuza head finds himself ensnared in the unwavering attentions of a sometimes impetuous but always cunning schemer. If being yakuza doesn’t get him killed, his new lover just might!\r\n\r\nUichi Rogi is in a bind, both literally and figuratively, when he meets Akitora Oyamato, the yakuza head who’s come to collect a debt from Uichi’s lover. Instantly smitten, Uichi concocts dubious and often dangerous schemes to get closer to the man, but his lies come at a heavy price—one he may regret having to pay for Akitora’s affections. ',
        14.00, 1),
       (2, 'Attack on Titan 1', '2022-09-22 13:42:53', '2022-09-22 13:42:53',
        'In this post-apocalytpic sci-fi story, humanity has been devastated by the bizarre, giant humanoids known as the Titans. Little is known about where they came from or why they are bent on consuming mankind. Seemingly unintelligent, they have roamed the world for years, killing everyone they see. For the past century, what\'s left of man has hidden in a giant, three-walled city. People believe their 50-meter-high walls will protect them from the Titans, but the sudden appearance of an immense Titan is about to change everything.',
        15.00, 2),
       (3, 'Berserk, Vol. 1', '2022-09-22 13:46:28', '2022-09-22 13:46:28',
        'Created by Kentaro Miura, Berserk is manga mayhem to the extreme - violent, horrifying, and mercilessly funny - and the wellspring for the internationally popular anime series. Not for the squeamish or the easily offended, Berserk asks for no quarter - and offers none!\r\nHis name is Guts, the Black Swordsman, a feared warrior spoken of only in whispers. Bearer of a gigantic sword, an iron hand, and the scars of countless battles and tortures, his flesh is also indelibly marked with The Brand, an unholy symbol that draws the forces of darkness to him and dooms him as their sacrifice. But Guts won\'t take his fate lying down; he\'ll cut a crimson swath of carnage through the ranks of the damned - and anyone else foolish enough to oppose him! Accompanied by Puck the Elf, more an annoyance than a companion, Guts relentlessly follows a dark, bloodstained path that leads only to death...or vengeance.',
        14.00, 3),
       (4, 'GTO: Great Teacher Onizuka Vol. 1', '2022-09-22 13:47:53', '2022-09-22 13:47:53',
        'Meet Eikichi Onizuka, a 22-year-old virgin and ex-biker. He\'s crude, foul-mouthed, and has a split-second temper. His goal: to be the Greatest High School Teacher in the world! Onizuka may think he\'s the toughest guy on campus, but when he meets his class full of bullies, blackmailers and scheming sadists, he\'ll have to prove it.',
        16.00, 4),
       (5, 'Mob Psycho 100 Volume 1 ', '2022-09-22 13:51:55', '2022-09-22 13:51:55',
        'From the creator of One-Punch Man and now a Netflix Live Action Series! Do you or someone you know need an exorcist who works cheap? Reigen\'s your guy!\r\n\r\nWhat\'s his secret to busting ghosts while keeping prices low? Well, first, he\'s a fraud, and second, he pays the guy who\'s got the real psychic power--his student assistant Shigeo--less than minimum wage. Shigeo is an awkward but kind boy whose urge to help others and get along with them is bound up with the mental safety locks he\'s placed on his own emotions. Reigen knows he needs to exploit Shigeo to stay in business, yet for better or worse he\'s also his mentor and counselor. And he also knows whenever the normally repressed kid\'s emotions reach level 100, it may unleash more psychic energy than either of them can handle! ',
        99.00, 5),
       (6, 'One-Punch Man, Vol. 1', '2022-09-22 13:53:33', '2022-09-22 13:53:33',
        ' Life gets pretty boring when you can beat the snot out of any villain with just one punch.\r\n\r\nNothing about Saitama passes the eyeball test when it comes to superheroes, from his lifeless expression to his bald head to his unimpressive physique. However, this average-looking guy has a not-so-average problem—he just can’t seem to find an opponent strong enough to take on!\r\n\r\nEvery time a promising villain appears, Saitama beats the snot out of ’em with one punch! Can he finally find an opponent who can go toe-to-toe with him and give his life some meaning? Or is he doomed to a life of superpowered boredom? ',
        21.00, 6),
       (7, 'Sasaki and Miyano, Vol. 1 ', '2022-09-22 13:54:20', '2022-09-22 13:54:20',
        'It all started like a typical old-school boys’ love plotline—bad-boy senior meets adorably awkward underclassman, one of them falls in love, and so on and so forth. But although Miyano is a self-proclaimed boys’ love expert, he hasn’t quite realized…he’s in one himself. Which means it’s up to Sasaki to make sure their story has a happily ever after…!',
        13.00, 7),
       (8, 'TWITTERING BIRDS NEVER FLY  VOL 01 ', '2022-09-22 13:56:11', '2022-09-22 13:56:11',
        'The sexually masochistic yakuza boss, Yashiro, isn\'t the type to warm up to others easily. But when Chikara Doumeki, his newly hired bodyguard, catches his interest, he reconsiders his \"hands-off\" policy with subordinates. As Yashiro\'s invitations fail, the yakuza boss finds out his bodyguard has a very personal reason for staying at arm\'s length.',
        21.00, 8),
       (9, 'JoJo\'s Bizarre Adventure: Part 1--Phantom Blood, Vol. 1', '2022-09-22 14:03:25', '2022-09-22 14:03:25',
        ' A multigenerational tale of the heroic Joestar family and their never-ending battle against evil!\r\n\r\nThe legendary Shonen Jump series, now available in English for the first time, in a deluxe edition featuring color pages and newly drawn cover art! JoJo’s Bizarre Adventure is a groundbreaking manga famous for its outlandish characters, wild humor and frenetic battles. A multigenerational tale of the heroic Joestar family and their never-ending battle against evil! \r\n\r\nYoung Jonathan Joestar’s life is forever changed when he meets his new adopted brother, Dio. For some reason, Dio has a smoldering grudge against him and derives pleasure from seeing him suffer. But every man has his limits, as Dio finds out. This is the beginning of a long and hateful relationship!  ',
        15.00, 9),
       (10, 'Killing Stalking: Deluxe Edition Vol. 1', '2022-09-22 14:04:18', '2022-09-22 14:04:18',
        '\r\nThe Mature-rated Boys\' Love horror webtoon from Lezhin that became a global manhwa hit! Get this chilling and thrilling tale in a deluxe, full-color paperback in English for the very first time.\r\n\r\nYoon Bum, a scrawny and quiet man, has a crush on one of the most popular and handsome guys in his college: Sangwoo. After the two cross paths again, Yoon Bum’s feelings grow in intensity until they become an obsession–and he breaks into Sangwoo’s home. But what he sees inside is not the Sangwoo of his fantasies; his dreams of this alluring man abruptly turn into a nightmare.\r\n\r\nThis wildly popular BL horror manhwa webtoon is the grand prize winner of the 2nd Lezhin Comics World Comic Contest, and has been published in numerous languages since its 2016 debut, winning critical praise across the globe. Now readers can own this full-color, deluxe paperback edition in English for the first time, with a special fold-out insert included in every volume! ',
        23.00, 10),
       (11, 'Seaside Stranger Vol. 1: Umibe no Étranger ', '2022-09-22 14:05:07', '2022-09-22 14:05:07',
        ' A love story between an openly gay novelist and a young man coping with grief that was recently turned into an anime film!\r\n\r\nEver since his parents disowned him for being gay, Shun has been living with his aunt on a small island near Okinawa. One day, he meets Mio, a high school student who recently lost his own parents and now spends his days sitting by the sea. The two young men begin to open up to each other...until Mio reveals that he\'s leaving. Three years later, an adult Mio returns to the island to confess his true feelings, but is Shun ready for a relationship? ',
        21.00, 11),
       (12, 'Love is an Illusion! Vol. 1', '2022-10-21 12:13:14', '2022-10-21 12:13:14',
        'Hye-sung spent his entire life believing he was an alpha, the jackpot of the genetic lottery. His world is flipped upside down when he learns that he isn’t a dominating alpha, but a submissive omega instead! His frustration redlines whenever he crosses paths with the handsome Dojin, a true alpha. Supposedly, Dojin can’t stand omegas, but sparks fly when he butts heads with Hye-sung, and their explosive arguments set off an unexpectedly spicy relationship. Is their sizzling chemistry truly just pheromones?!',
        16.98, 12),
       (13, 'Hyperventilation ', '2022-10-21 12:15:56', '2022-10-21 12:15:56',
        'Myongi and Sunho haven\'t seen each other since they were 18 years old. Now 27, they run into one another at a high school reunion, and the romantic feelings they had for one another come rushing to the surface. But are they the same people now that they were back then?',
        17.00, 13),
       (14, 'FANGS, Volume 1', '2022-10-21 12:19:28', '2022-10-21 12:19:28',
        'As the sole survivor of a vampire attack, En wakes up to find that his hair has gone white as snow... and, worse, that he\'s developed a craving of his own for blood.\r\n\r\nFortunately, the vampire health and welfare organization FANGS is there to help with the transition, and the handsome Ichii steps up as his guardian and mentor. Swept up into a confusing and lonely new world where everyone seems to be hankering for a taste of his \"virgin\" blood, En must navigate the FANGS pairing system, an arrangement that sets up compatible vampires as mutual feeding partners... and partners in all other ways as well.  But what happens when En panics and declares that he\'ll be paired with Ichii?  And what does brooding, rough-around-the-edges Ichii make of his spunky, outgoing new charge?',
        13.82, 14);


INSERT INTO `image`
VALUES (1, 'Jealousy, Vol. 1 cover', 'https://thumbsnap.com/i/JGSzhtjq.jpg', now(), now()),
       (2, 'Attack on Titan 1 cover', 'https://thumbsnap.com/i/yGNMtYZi.jpg', now(), now()),
       (3, 'Berserk, Vol. 1 cover', 'https://thumbsnap.com/i/tYVjyMfa.jpg', now(), now()),
       (4, 'GTO: Great Teacher Onizuka Vol. 1 cover', 'https://thumbsnap.com/i/tAFwZvqh.jpg', now(), now()),
       (5, 'Mob Psycho 100 Volume 1  cover', 'https://thumbsnap.com/i/WHYFTgUn.jpg', now(), now()),
       (6, 'One-Punch Man, Vol. 1 cover', 'https://thumbsnap.com/i/tPJTFnqT.jpg', now(), now()),
       (7, 'Sasaki and Miyano, Vol. 1  cover', 'https://thumbsnap.com/i/TfZmvdrX.jpg', now(), now()),
       (8, 'TWITTERING BIRDS NEVER FLY  VOL 01  cover', 'https://thumbsnap.com/i/rr8gf1JW.jpg', now(), now()),
       (9, 'JoJo\'s Bizarre Adventure: Part 1--Phantom Blood, Vol. 1 cover', 'https://thumbsnap.com/i/XZBBdQ2F.jpg',
        now(), now()),
       (10, 'Killing Stalking: Deluxe Edition Vol. 1 cover', 'https://thumbsnap.com/i/wV39A5AR.jpg', now(), now()),
       (11, 'Seaside Stranger Vol. 1: Umibe no Étranger  cover', 'https://thumbsnap.com/i/oQjUkH8R.jpg', now(), now()),
       (12, 'Love is an Illusion! Vol. 1 cover', 'https://thumbsnap.com/i/9cTa6cF7.jpg', now(), now()),
       (13, 'Hyperventilation  cover', 'https://thumbsnap.com/i/k15PR9W1.jpg', now(), now()),
       (14, 'FANGS, Volume 1  cover', 'https://thumbsnap.com/i/EzvRgToK.jpg', now(), now());


INSERT INTO `topic`
VALUES (1, 'Dystopian'),
       (2, 'Sci-Fi'),
       (3, 'Horror'),
       (4, 'Media Tie-In'),
       (5, 'Adventure'),
       (6, 'Yaoi'),
       (7, 'Thriller'),
       (8, 'Romance'),
       (10, 'Shonen'),
       (11, 'Superhero'),
       (12, 'Fantasy'),
       (13, 'LGBTQ+');

INSERT INTO `publication_has_topic`
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (3, 3),
       (5, 3),
       (9, 3),
       (3, 4),
       (4, 5),
       (1, 6),
       (7, 6),
       (8, 6),
       (11, 6),
       (1, 7),
       (1, 8),
       (5, 2),
       (6, 2),
       (9, 2),
       (9, 10),
       (6, 11),
       (11, 11),
       (6, 12),
       (7, 13),
       (11, 13),
       (12, 6),
       (12, 12),
       (12, 13),
       (13, 6),
       (13, 8),
       (14, 6),
       (14, 18),
       (14, 12);

INSERT INTO `person_has_publication`
VALUES (1, 1, NULL),
       (1, 2, NULL);


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
