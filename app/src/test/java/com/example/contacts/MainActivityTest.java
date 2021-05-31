package com.example.contacts;

import static org.junit.Assert.assertNotEquals;

import junit.framework.TestCase;

import org.junit.Test;

public class MainActivityTest extends TestCase {
    private final String[] name_male = {"Oleg", "Igor", "Sergey", "Mike", "Jack", "Tonny", "Nick", "Mark", "Luke"};
    private static final String[] name_female = {"Anna", "Nataliya", "Masha", "Alexandra", "Tanya", "Sonya", "Jessica", "Vera", "Olga"};

    @Test
    public void testNameByGender() { // you can't call the person Anna if he is a male
        String name = RandomPerson.getRandomName("Male");
        for (String s : name_female) {
            assertNotEquals(name, s);
        }
        name = RandomPerson.getRandomName("Female");
        for (String s : name_male) {
            assertNotEquals(name, s);
        }
    }
    
    @Test
    public void testRandom() { // test the random compatibility 10 different times (if the male is online he can't have a Female avatar and avatar can't show him as online)
        for(int i = 0; i < 10; i++){
            String gender = RandomPerson.getRandomGender();
            boolean online = RandomPerson.getRandomOnline();
            int imageID = RandomPerson.getAvatar(gender, online);
            if(online){
                if(gender.equals("Male"))
                    assertEquals(imageID, R.drawable.avatar_icon_online);
                else
                    assertEquals(imageID, R.drawable.icon_female_online);
            }
            else{
                if(gender.equals("Male"))
                    assertEquals(imageID, R.drawable.avatar_icon);
                else
                    assertEquals(imageID, R.drawable.icon_female);
            }
        }
    }
}