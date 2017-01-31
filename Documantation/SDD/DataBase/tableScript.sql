SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`UserDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`UserDetail` (
  `idUserDetail` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(100) NOT NULL,
  `LastName` VARCHAR(100) NOT NULL,
  `EMail` VARCHAR(100) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUserDetail`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`TagDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`TagDetail` (
  `idTagDetail` INT NOT NULL AUTO_INCREMENT,
  `TagName` VARCHAR(100) NOT NULL,
  `Description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idTagDetail`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`Domain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`Domain` (
  `idDomain` INT NOT NULL AUTO_INCREMENT,
  `idUserDetail` INT NOT NULL,
  `Domain` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idDomain`),
  INDEX `idUserDetail_idx` (`idUserDetail` ASC),
  CONSTRAINT `idUserDetail`
    FOREIGN KEY (`idUserDetail`)
    REFERENCES `SpellRaterDB`.`UserDetail` (`idUserDetail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`PathDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`PathDetail` (
  `idPathDetail` INT NOT NULL AUTO_INCREMENT,
  `idDomain` INT NOT NULL,
  `Path` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idPathDetail`),
  INDEX `idDomain_idx` (`idDomain` ASC),
  CONSTRAINT `idDomain`
    FOREIGN KEY (`idDomain`)
    REFERENCES `SpellRaterDB`.`Domain` (`idDomain`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`ErrorSummary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`ErrorSummary` (
  `idErrorSummary` INT NOT NULL AUTO_INCREMENT,
  `idPathDetail` INT NOT NULL,
  `DateTime` DATETIME NOT NULL,
  `Image` LONGBLOB NULL,
  `WordCount` INT NOT NULL,
  PRIMARY KEY (`idErrorSummary`),
  INDEX `idPathDetail_idx` (`idPathDetail` ASC),
  CONSTRAINT `idPathDetail`
    FOREIGN KEY (`idPathDetail`)
    REFERENCES `SpellRaterDB`.`PathDetail` (`idPathDetail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`ErrorDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`ErrorDetail` (
  `idErrorDetail` INT NOT NULL,
  `idTagDetail` INT NOT NULL,
  `RawNo` INT NULL,
  `ErrorName` VARCHAR(100) NULL,
  PRIMARY KEY (`idErrorDetail`),
  INDEX `idTagDetail_idx` (`idTagDetail` ASC),
  CONSTRAINT `idTagDetail`
    FOREIGN KEY (`idTagDetail`)
    REFERENCES `SpellRaterDB`.`TagDetail` (`idTagDetail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SpellRaterDB`.`ErrorDetailhasErrorSummary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpellRaterDB`.`ErrorDetailhasErrorSummary` (
  `idErrorDetail` INT NOT NULL,
  `idErrorSummary` INT NOT NULL,
  PRIMARY KEY (`idErrorDetail`, `idErrorSummary`),
  INDEX `fk_ErrorDetail_has_ErrorSummary_ErrorSummary1_idx` (`idErrorSummary` ASC),
  INDEX `fk_ErrorDetail_has_ErrorSummary_ErrorDetail1_idx` (`idErrorDetail` ASC),
  CONSTRAINT `idErrorDetail`
    FOREIGN KEY (`idErrorDetail`)
    REFERENCES `SpellRaterDB`.`ErrorDetail` (`idErrorDetail`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idErrorSummary`
    FOREIGN KEY (`idErrorSummary`)
    REFERENCES `SpellRaterDB`.`ErrorSummary` (`idErrorSummary`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
