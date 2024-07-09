package com.minod.shop.service;

import com.minod.shop.domain.Item.Book;
import com.minod.shop.domain.Item.Item;
import com.minod.shop.domain.Member;
import com.minod.shop.domain.Order;
import com.minod.shop.domain.status.OrderStatus;
import com.minod.shop.domain.valuetype.Address;
import com.minod.shop.exception.NotEnoughStockException;
import com.minod.shop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderServiceTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired    OrderService orderService;
    @Autowired    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        // given
//        Member member = new Member(); // 테스트마다 자주 쓰일 것 같아... createMember, cerate상품 메서드를 테스트에서 구현해 쓴다.
        Member member = createMember();
        Item item = createBook("책01", 1000, 29);

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), 2); // 주문하기, Order 생성 시작

        // then
        Order getOrder = orderRepository.findOne(orderId); // 리포지터리에서 불러와서 비교하기 (Order 만들어져 영속화된걸 가지고 테스트...)

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "status가 enum의 ORDER인지");
        assertEquals(1, getOrder.getOrderItems().size(), "상품종류 1개");
        assertEquals(2000, getOrder.getTotalPrice(), "전체가격");
        assertEquals(27, item.getStockQuantity(), "2개 줄어서 27개가 되어야");
    }

    @Test
    public void 상품주문_재고초과() throws Exception {
        // given
        Member member = createMember();
        Item item = createBook("책02", 1000, 29);

        // when
        int count = 31;

        // then
//        orderService.order(member.getId(), item.getId(), count);
//        assertThrows(NotEnoughStockException.class, () -> {orderService.order(member.getId(), item.getId(), count);});

        /* 방법2 */
        orderService.order(member.getId(), item.getId(), count);

        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 상품주문_취소() throws Exception {
        // given
        Member member = createMember();
        Item item = createBook("책03", 1000, 29);
        // when
        Long orderId = orderService.order(member.getId(), item.getId(), 2); // 주문하기, Order 생성 시작
        Order getOrder = orderRepository.findOne(orderId); // 리포지터리에서 order 불러오기
        // then
//        getOrder.cancel(); // 엔티티에서 직접 호출하는게 아닌, 서비스를 통해서 수행.!
        orderService.cancelOrder(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "status가 enum의 CANCEL인지, 취소됫는지 확인");
        assertEquals(item.getStockQuantity(), 29, "item 개수 원래대로 돌아왔는지 확인");
    }

    @Test
    public void 상품생성_Book() throws Exception {
        Item item = createBook("책04", 1000, 29);
    }

    @Test
    public void 상품생성_Album() throws Exception {
        Item item = createBook("책05", 1000, 29);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}