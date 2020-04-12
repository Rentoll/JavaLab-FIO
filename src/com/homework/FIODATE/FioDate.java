package com.homework.FIODATE;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FioDate {
    private String name, secondName, thirdName, sex;
    private LocalDate birthDate;
    private int age;
    Scanner in = new Scanner(System.in);

    boolean input() {
        System.out.println("Введите ваше ФИО и дату рождения русскими буквами в формате: Имя Фамилия Отчество день.месяц.год");
        name = in.next();
        if(!checkCorrectInput(name)) {
            System.out.println("Invalid name input!");
            return false;
        }
        secondName = in.next();
        if(!checkCorrectInput(secondName)) {
            System.out.println("Invalid surname input!");
            return false;
        }
        thirdName = in.next();
        if(!checkCorrectInput(thirdName)) {
            System.out.println("Invalid patronymic input!");
            return false;
        }
        String tmp = in.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d.M.yyyy");;
        if(checkCorrectInput(tmp, dateFormat) ) {
            birthDate = LocalDate.parse(tmp, dateFormat);
            LocalDate curDate = LocalDate.now();
            age = Period.between(birthDate, curDate).getYears();
        }
        else {
            System.out.println("Invalid date input!");
            return false;
        }
        sex = findSex();
        return true;
    }

    private boolean checkCorrectInput(String inputData) {
        if(inputData == null) {
            return false;
        }
        if(inputData.matches("^[А-Яа-я]+$")) {
            return true;
        }
        return false;
    }

    private boolean checkCorrectInput(String date, DateTimeFormatter formatter) {
        try {
            formatter.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String findSex() {
        String s = "";
        if(thirdName.contains("ович") || thirdName.contains("евич") || thirdName.contains("ич")) {
            s = "мужской";
        }
        if(thirdName.contains("овна") || thirdName.contains("евна") || thirdName.contains("ична")) {
            s = "женский";
        }
        if(s == "") {
            s = "кот";
        }
        return s;
    }

    void output() {
        System.out.println(secondName + " " + name.charAt(0) + "." + thirdName.charAt(0) + ". | Возраст: " + age + ". | Пол: " + sex);
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
