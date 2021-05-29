package com.example.contacts;

public class RandomPerson {
    private static final String[] name_male = {"Oleg", "Igor", "Sergey", "Mike", "Jack", "Tonny", "Nick", "Mark", "Luke"};
    private static final String[] name_female = {"Anna", "Nataliya", "Masha", "Alexandra", "Tanya", "Sonya", "Jessica", "Vera", "Olga"};
    private static final String[] surname = {"Storozhev", "Ivanov", "Sikorsky", "Tvist", "Green", "Tsivinskiy", "Melnik", "Vorotov", "Govologorov"};
    private static final String[] gender = {"Male", "Female"};

    public static String getRandomName(String gender){
        int random;
        if(gender.equals("Male")) {
            random = (int) (Math.random() * name_male.length);
            return name_male[random];
        }
        else {
            random = (int) (Math.random() * name_female.length);
            return name_female[random];
        }
    }
    public static String getRandomSurname(){
        int random = (int) (Math.random()*surname.length);
        return surname[random];
    }
    public static String getRandomGender(){
        int random = (int) (Math.random()*2);
        return gender[random];
    }
    public static boolean getRandomOnline(){
        java.util.Random random = new java.util.Random();
        return random.nextBoolean();
    }
}
