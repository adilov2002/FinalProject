import com.example.FinalProject.entities.Categories;
import com.example.FinalProject.entities.Places;
import com.example.FinalProject.repositories.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class Testing {

    @Test
    public void testAllUsers(){
        //given
        UsersRepostiory usersRepostiory = new UsersRepostiory();

        //expect
        assertNotNull(usersRepostiory.getAllUsers());
    }

    @Test
    public void testAllCategories(){
        //given
        CategoryRepository categoryRepository = new CategoryRepository();

        //expect
        assertNotNull(categoryRepository.getAllCategories());
    }


    @Test
    public void testRoles(){
        //given
        RolesRepository rolesRepository = new RolesRepository();

        //expect
        assertEquals(rolesRepository.getRoleById(1L).getName(), "ROLE_ADMIN");
        assertEquals(rolesRepository.getRoleById(2L).getName(), "ROLE_USER");
    }

    @Test
    public void testLogs(){
        //given
        LogRepository logRepository = new LogRepository();

        //expect
        assertNotNull(logRepository.getAllCustomLogs());
    }

    @Test
    public void testAddingPlace(){
        //given
        PlacesRepository placesRepository = new PlacesRepository();
        Places places = new Places(14L, "test", "test", "test", new Categories(12L, "test"));

        //expect
        assertTrue(placesRepository.addPlace(places));
    }

    @Test
    public void testDeletePlace(){
        //given
        PlacesRepository placesRepository = new PlacesRepository();
        Long id = 14L;

        //expect
        assertTrue(placesRepository.deletePlaceById(id));
    }

    @Test
    public void testNewsTypes(){
        //given
        NewsTypeRepository newsTypeRepository = new NewsTypeRepository();

        //expect
        assertNotNull(newsTypeRepository.getNewsTypeById(4L));
    }



}
