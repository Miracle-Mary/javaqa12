package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AlreadyExistsException;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

public class ShopRepositoryTest {
    Product item1 = new Product(11, "хлеб", 40);
    Product item2 = new Product(222, "булка", 30);
    Product item3 = new Product(3, "картошка", 20);
    Product item4 = new Product(3, "батат", 25);

    @Test
    public void shouldAddItems() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Product[] expected = {item1, item2, item3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExistsException() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item4);
        });
    }


    @Test
    public void shouldThrowNotFoundException() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(10);
        });
    }

    @Test
    public void shouldRemoveItem() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        repo.removeById(222);

        Product[] expected = {item1, item3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
}
