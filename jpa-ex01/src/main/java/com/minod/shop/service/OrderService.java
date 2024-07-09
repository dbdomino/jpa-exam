package com.minod.shop.service;

import com.minod.shop.domain.Delivery;
import com.minod.shop.domain.Item.Item;
import com.minod.shop.domain.Member;
import com.minod.shop.domain.Order;
import com.minod.shop.domain.OrderItem;
import com.minod.shop.domain.status.DeliveryStatus;
import com.minod.shop.repository.ItemRepository;
import com.minod.shop.repository.MemberRepository;
import com.minod.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** Long order(Long memberId, Long itemId, int count)
 *  void cancelOrder(Long orderId)
 *  List<Order> findOrders(OrderSearch orderSearch)
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문하기, 주문 생성 실행
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 기본적인 생성.
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 만들기
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress()); // 배송 주소는 member에 있는걸 그대로 가져다씀.
        delivery.setStatus(DeliveryStatus.READY);

        // 주문상품 생성
//        OrderItem orderItem = new OrderItem() 생성자 생성말고, 생성메서드 create로 주문상품 다대다 연관관계의 객체 생성
        OrderItem orderItem = OrderItem.create(item, item.getPrice(), count);

        // 주문 생성
//        Order order = new Order();  생성자 생성말고, 생성메서드 create로 구현한 생성메서드 사용
        Order order = Order.create(member, delivery, orderItem);

        // 생성한 주문을 DB에 저장
        orderRepository.save(order);
        return order.getId();
    }

    // 주문취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문엔티티 가져오기
        Order order = orderRepository.findOne(orderId);
        // 취소하기
        order.cancel();
    }

    // 주문 검색? 리스트로 주문내역 출력!
//    public List<Order> findOrders(OrderSearch orderSearch) {
//
//    }


}
