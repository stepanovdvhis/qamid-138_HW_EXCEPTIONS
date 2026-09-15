package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product book = new Product(1, "Book", 500);
        Product pen = new Product(2, "Pen", 50);
        repo.add(book);
        repo.add(pen);

        repo.removeById(1);

        Product[] expected = {pen};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenRemovingNonExistentProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Book", 500));

        Assertions.assertThrows(
                NotFoundException.class,
                () -> repo.removeById(999)
        );
    }

    @Test
    public void shouldAddNewProduct() {
        ShopRepository repo = new ShopRepository();
        Product book = new Product(1, "Book", 500);

        repo.add(book);

        Product[] expected = {book};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsExceptionWhenAddingDuplicateId() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Book", 500));

        Assertions.assertThrows(
                AlreadyExistsException.class,
                () -> repo.add(new Product(1, "Another book", 700))
        );
    }
}