package com.example.backendsp.web;


import com.example.backendsp.DAO.entities.Cart;
import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.DAO.entities.Order;
import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.OrderRepository;
import com.example.backendsp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartControlle {
    @Autowired
    private CartServices cartServices;

    @Autowired
    private MangaServices mangaService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private OrderServices orderService;
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartServices.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
    @CrossOrigin("*")
    @GetMapping("/{userId}")
    public Collection<Manga> getCartByUserId(@PathVariable("userId") Long userId) {
        Optional<Cart> cartOptional = cartServices.findByUserId(userId);
        Collection<Manga> mangas = cartOptional.get().getMangas();
        return mangas;
    }
//    @PostMapping
//    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
//        Cart createdCart = cartServices.createCart(cart);
//        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
//    }
    @PostMapping("/add/{userId}")
    public  ResponseEntity<Cart> addToCart(@PathVariable Long userId ,@RequestBody Long mangaID){
        User user =userServices.
                getUserById(userId);
        System.out.println("----------------------------------------------------");
        System.out.println(user);
        System.out.println("----------------------------------------------------");

        Cart cart= user.getCart();
        System.out.println("----------------------------------------------------");
        System.out.println(user.getCart());
        System.out.println("----------------------------------------------------");
        if (user.getCart()!=null){
            cart.getMangas().add(mangaService.getMangaById(mangaID));
            cart.setQuantity(cart.getMangas().size());
            user.setCart(cart);
            userServices.saveUser(user);
            cartServices.addToCart(cart);
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        }
        Cart cart1 = new Cart();
        user.setId(user.getId());
        cart1.setUser(user);
        cart1.setMangas(List.of(mangaService.getMangaById(mangaID)));
        cart1.setQuantity(cart1.getMangas().size());
        cartServices.addToCart(cart1);
        user.setCart(cart1);
        return new ResponseEntity<>(cart1, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart cart = cartServices.updateCart(id, updatedCart);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long userid,@RequestBody Long mangaid) {
        cartServices.deleteMangaFromCart(userid,mangaid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Order> checkout(@PathVariable Long userId) {
        User user =userServices.
                getUserById(userId);
        Cart cart= user.getCart();
        Order order=new Order();
        if(user.getCart()!=null){
            order.setMangaList(cart.getMangas());
            orderRepository.save(order);

        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
