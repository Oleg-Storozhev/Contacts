package com.example.contacts;

import java.util.Random;

public class RandomPerson {
    private static final String[] name_male = {"Oleg", "Igor", "Sergey", "Mike", "Jack", "Tonny", "Nick", "Mark", "Luke"};
    private static final String[] name_female = {"Anna", "Nataliya", "Maria", "Alexandra", "Tanya", "Sonya", "Lera", "Vera", "Olga"};
    private static final String[] surname = {"Storozhev", "Iliyashenko", "Lishenko", "Tvist", "Green", "Repnin", "Melnik", "Kulik", "Semchenko"};
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
        Random random = new Random();
        return random.nextBoolean();
    }
    public static int getAvatar(String gender, boolean online){
        if(gender.equals("Female")) {
            if(online){
                return R.drawable.icon_female_online;
            }
            return R.drawable.icon_female;
        }
        else {
            if(online){
                return R.drawable.avatar_icon_online;
            }
            return R.drawable.avatar_icon;
        }
    }
}
