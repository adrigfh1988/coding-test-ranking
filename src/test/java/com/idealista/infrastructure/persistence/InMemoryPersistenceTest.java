package com.idealista.infrastructure.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.idealista.infrastructure.utils.Constants.INCORRECT_ID_PICTURE;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryPersistenceTest {

    @Test
    @DisplayName("Test to verify the findByIdIn functionality")
    void findByIdInOkTest() {

        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        List<PictureVO> query = inMemoryPersistence.findByIdIn(Arrays.asList(1, 2, 3));

        List<PictureVO> expectedPictures = new ArrayList<>();
        expectedPictures.add(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"));
        expectedPictures.add(new PictureVO(2, "http://www.idealista.com/pictures/2", "HD"));
        expectedPictures.add(new PictureVO(3, "http://www.idealista.com/pictures/3", "SD"));

        assertArrayEquals(expectedPictures.toArray(), query.toArray());
    }

    @Test
    @DisplayName("Test to verify the failure of finding pictures with negative ID")
    void findByIdInFailNoIdTest(){
        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> inMemoryPersistence.findByIdIn(Collections.singletonList(-1)));
        assertEquals(INCORRECT_ID_PICTURE,illegalArgumentException.getMessage());
    }
}
