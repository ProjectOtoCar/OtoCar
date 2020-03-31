package com.otocar.otocar.service;

import com.otocar.otocar.enums.TypeAccount;
import com.otocar.otocar.model.Image;
import com.otocar.otocar.model.Seller;
import com.otocar.otocar.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @Mock
    ImageRepository imageRepository;

    @InjectMocks
    ImageService imageService;

    Image image;
    final boolean isMainImage = false;

    final Long id = 1L;
    @BeforeEach
    void setUp() {
        image = new Image();
        image.setId(id);
        image.setMainImage(isMainImage);
    }

    @Test
    void findById() {
        given(imageRepository.findById(anyLong())).willReturn(Optional.of(image));

        Image imageFound = imageService.findById(anyLong());

        then(imageRepository).should().findById(anyLong());
        then(imageRepository).shouldHaveNoMoreInteractions();
        assertThat(imageFound).isNotNull();
        assertThat(imageFound.getId()).isEqualTo(id);
    }
    @Test
    void findByIdNotFound() {
        given(imageRepository.findById(anyLong())).willReturn(Optional.empty());

        Image imageFound = imageService.findById(anyLong());

        then(imageRepository).should().findById(anyLong());
        then(imageRepository).shouldHaveNoMoreInteractions();
        assertThat(imageFound).isNull();
    }
    @Test
    void findAll() {
        final Long id1 = 2L;
        Image image1 = new Image();
        image1.setId(id1);
        List<Image> imageList = new ArrayList<>();
        imageList.add(image);
        imageList.add(image1);

        given(imageRepository.findAll()).willReturn(imageList);

        Iterable<Image> imageIterableFound = imageService.findAll();
        List<Image> imageListFound = new ArrayList<>();

        imageIterableFound.forEach(imageListFound::add);

        then(imageRepository).should().findAll();
        then(imageRepository).shouldHaveNoMoreInteractions();
        assertThat(imageIterableFound).isNotNull();
        assertThat(imageListFound.size()).isEqualTo(imageList.size());

        assertThat(imageListFound.get(0).getId()).isEqualTo(id);
        assertThat(imageListFound.get(1).getId()).isEqualTo(id1);


    }
    @Test
    void deleteById() {
        imageService.deleteById(anyLong());

        then(imageRepository).should().deleteById(anyLong());
        then(imageRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void save() {
        lenient().when(imageRepository.save(image)).thenReturn(image);

        Image imageFound = imageService.save(image);

        then(imageRepository).should().save(any(Image.class));
        then(imageRepository).shouldHaveNoMoreInteractions();

        assertThat(imageFound.getId()).isEqualTo(image.getId());
    }

    @Test
    void changeObjectExist() {
        given(imageRepository.findById(anyLong())).willReturn(Optional.of(image));
        given(imageRepository.save(image)).willReturn(image);

        Image imageFound = imageService.change(anyLong(), image);

        then(imageRepository).should().findById(anyLong());
        then(imageRepository).should().save(any(Image.class));
        then(imageRepository).shouldHaveNoMoreInteractions();

        assertThat(imageFound.getId()).isEqualTo(image.getId());
    }

    @Test
    void changeObjectNotExist() {
        given(imageRepository.findById(anyLong())).willReturn(Optional.empty());
        given(imageRepository.save(image)).willReturn(image);

        Image imageFound = imageService.change(anyLong(), image);

        then(imageRepository).should().findById(anyLong());
        then(imageRepository).should().save(any(Image.class));
        then(imageRepository).shouldHaveNoMoreInteractions();

        assertThat(imageFound).isNotNull();
    }

    @Test
    void patchNotChange() {
        Map<String, Object> fields = new HashMap<>();
        given(imageRepository.findById(anyLong())).willReturn(Optional.of(image));
        imageService.patch(id,fields);

        then(imageRepository).should().findById(anyLong());
        then(imageRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(0);
    }
    @Test
    void patchWithWrongKey() {
        Map<String, Object> fields = new HashMap<>();
        given(imageRepository.findById(anyLong())).willReturn(Optional.of(image));
        fields.put("wrong","wrong");
        imageService.patch(id,fields);


        then(imageRepository).should().findById(anyLong());
        then(imageRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }

    static Stream<Arguments> getArgsToPatchChange() {
        return Stream.of(Arguments.of("isMainImage", "false"),
                Arguments.of("photo", new byte[100].toString())
                ) ;
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgsToPatchChange")
    void patchChange(String key, Object value) {
        Map<String, Object> fields = new HashMap<>();
        given(imageRepository.findById(anyLong())).willReturn(Optional.of(image));
        fields.put(key,value);
        imageService.patch(id,fields);


        then(imageRepository).should().findById(anyLong());
        then(imageRepository).should().save(any(Image.class));
        then(imageRepository).shouldHaveNoMoreInteractions();

        assertThat(fields.size()).isEqualTo(1);
    }
}