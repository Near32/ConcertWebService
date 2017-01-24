SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `ConcertWebServiceDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ConcertWebServiceDB` ;

-- -----------------------------------------------------
-- Table `ConcertWebServiceDB`.`CustomerTable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConcertWebServiceDB`.`CustomerTable` (
  `idCustomer` INT NOT NULL,
  `firstName` TEXT NOT NULL,
  `lastName` TEXT NOT NULL,
  `email` TEXT NOT NULL,
  `address` TEXT NULL,
  `phoneNumber` TINYTEXT NULL,
  `password` TEXT NULL,
  PRIMARY KEY (`idCustomer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConcertWebServiceDB`.`HallTable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConcertWebServiceDB`.`HallTable` (
  `idHall` INT NOT NULL,
  `name` TEXT NOT NULL,
  `address` TEXT NULL,
  `seatCapacity` INT NULL,
  `seatCapacityRankA` INT NULL,
  `seatCapacityRankB` INT NULL,
  `seatCapacityRankC` INT NULL,
  `seatCapacityRankD` INT NULL,
  `seatCapacityRankS` INT NULL,
  PRIMARY KEY (`idHall`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConcertWebServiceDB`.`ConcertTable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConcertWebServiceDB`.`ConcertTable` (
  `idConcert` INT NOT NULL,
  `name` TEXT NOT NULL,
  `players` TEXT NULL,
  `description` LONGTEXT NULL,
  `date` TEXT NULL,
  `HallTable_idHall` INT NOT NULL,
  PRIMARY KEY (`idConcert`, `HallTable_idHall`),
  INDEX `fk_ConcertTable_HallTable1_idx` (`HallTable_idHall` ASC),
  CONSTRAINT `fk_ConcertTable_HallTable1`
    FOREIGN KEY (`HallTable_idHall`)
    REFERENCES `ConcertWebServiceDB`.`HallTable` (`idHall`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ConcertWebServiceDB`.`OrderTable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ConcertWebServiceDB`.`OrderTable` (
  `idOrder` INT NOT NULL,
  `quantitySeatA` INT NULL,
  `quantitySeatB` INT NULL,
  `quantitySeatC` INT NULL,
  `quantitySeatD` INT NULL,
  `quantitySeatS` INT NULL,
  `CustomerTable_idCustomer` INT NOT NULL,
  `ConcertTable_idConcert` INT NOT NULL,
  PRIMARY KEY (`idOrder`, `CustomerTable_idCustomer`, `ConcertTable_idConcert`),
  INDEX `fk_OrderTable_CustomerTable1_idx` (`CustomerTable_idCustomer` ASC),
  INDEX `fk_OrderTable_ConcertTable1_idx` (`ConcertTable_idConcert` ASC),
  CONSTRAINT `fk_OrderTable_CustomerTable1`
    FOREIGN KEY (`CustomerTable_idCustomer`)
    REFERENCES `ConcertWebServiceDB`.`CustomerTable` (`idCustomer`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTable_ConcertTable1`
    FOREIGN KEY (`ConcertTable_idConcert`)
    REFERENCES `ConcertWebServiceDB`.`ConcertTable` (`idConcert`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `ConcertWebServiceDB`.`CustomerTable`
-- -----------------------------------------------------
START TRANSACTION;
USE `ConcertWebServiceDB`;
INSERT INTO `ConcertWebServiceDB`.`CustomerTable` (`idCustomer`, `firstName`, `lastName`, `email`, `address`, `phoneNumber`, `password`) VALUES (0, 'Kevin', 'Denamganai', 'denamganai.kevin@gmail.com', 'osaka', '+33640118289', 'admin');
INSERT INTO `ConcertWebServiceDB`.`CustomerTable` (`idCustomer`, `firstName`, `lastName`, `email`, `address`, `phoneNumber`, `password`) VALUES (1, 'John', 'Doe', 'john@doe', 'unknown', 'unknown', 'unknown');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ConcertWebServiceDB`.`HallTable`
-- -----------------------------------------------------
START TRANSACTION;
USE `ConcertWebServiceDB`;
INSERT INTO `ConcertWebServiceDB`.`HallTable` (`idHall`, `name`, `address`, `seatCapacity`, `seatCapacityRankA`, `seatCapacityRankB`, `seatCapacityRankC`, `seatCapacityRankD`, `seatCapacityRankS`) VALUES (0, 'osaka hall', 'osaka', 1000, 500, 100, 100, 100, 200);
INSERT INTO `ConcertWebServiceDB`.`HallTable` (`idHall`, `name`, `address`, `seatCapacity`, `seatCapacityRankA`, `seatCapacityRankB`, `seatCapacityRankC`, `seatCapacityRankD`, `seatCapacityRankS`) VALUES (1, 'kyoto hall', 'kyoto', 2000, 1000, 500, 200, 100, 200);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ConcertWebServiceDB`.`ConcertTable`
-- -----------------------------------------------------
START TRANSACTION;
USE `ConcertWebServiceDB`;
INSERT INTO `ConcertWebServiceDB`.`ConcertTable` (`idConcert`, `name`, `players`, `description`, `date`, `HallTable_idHall`) VALUES (0, 'OsakaFest', 'Many', 'Enjoy performance from many bands in Osaka.', '25/01/2017', 0);
INSERT INTO `ConcertWebServiceDB`.`ConcertTable` (`idConcert`, `name`, `players`, `description`, `date`, `HallTable_idHall`) VALUES (1, 'Beethoven2017', 'Hilary Hahn', 'Beethoven\'s most beautiful violon pieces, performed by Hilary Hahn', '01/02/2017', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ConcertWebServiceDB`.`OrderTable`
-- -----------------------------------------------------
START TRANSACTION;
USE `ConcertWebServiceDB`;
INSERT INTO `ConcertWebServiceDB`.`OrderTable` (`idOrder`, `quantitySeatA`, `quantitySeatB`, `quantitySeatC`, `quantitySeatD`, `quantitySeatS`, `CustomerTable_idCustomer`, `ConcertTable_idConcert`) VALUES (0, 1, 0, 0, 0, 0, 0, 0);
INSERT INTO `ConcertWebServiceDB`.`OrderTable` (`idOrder`, `quantitySeatA`, `quantitySeatB`, `quantitySeatC`, `quantitySeatD`, `quantitySeatS`, `CustomerTable_idCustomer`, `ConcertTable_idConcert`) VALUES (1, 1, 0, 0, 0, 0, 0, 1);

COMMIT;

