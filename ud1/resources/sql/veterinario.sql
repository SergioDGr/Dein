-- MySQL Script generated by MySQL Workbench
-- Tue Jan  9 12:06:43 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Veterinario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Veterinario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Veterinario` ;
USE `Veterinario` ;

-- -----------------------------------------------------
-- Table `Veterinario`.`Animal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Veterinario`.`Animal` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NULL,
  `Sexo` VARCHAR(45) NULL,
  `Edad` INT NULL,
  `Peso` INT NULL,
  `Fecha_Primera_Consulta` VARCHAR(45) NULL,
  `Foto` BLOB NULL,
  `Raza` VARCHAR(45) NULL,
  `Especie` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Veterinario`.`Consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Veterinario`.`Consulta` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL,
  `IDAnimal` INT NOT NULL,
  `Observacion` VARCHAR(150) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Consulta_Animal_idx` (`IDAnimal` ASC) VISIBLE,
  CONSTRAINT `fk_Consulta_Animal`
    FOREIGN KEY (`IDAnimal`)
    REFERENCES `Veterinario`.`Animal` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;