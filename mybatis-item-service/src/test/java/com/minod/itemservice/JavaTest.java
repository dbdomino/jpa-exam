package com.minod.itemservice;

import com.minod.itemservice.domain.DeliveryCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

@Slf4j
public class JavaTest {
    @Test
    void JavaTest01() {
        String a = "RE";
        String b = "RE";

        log.info("a : {}",a);
        System.out.println(a);
        System.out.println(a.getClass());
        log.info("a.hashcode : {}",a.hashCode());
        System.out.println(b);
        System.out.println(b.getClass());
        log.info("b : {}",b);
        log.info("b.hashcode : {}",b.hashCode());
    }
    @Test
    void JavaTest02() {
        DeliveryCode d1 = new DeliveryCode();
        d1.setCode("01");
        d1.setDisplayName("display1");


        DeliveryCode d2 = new DeliveryCode();
        d2.setCode("01");
        d2.setDisplayName("display1");
        System.out.println(d1);
        System.out.println("d1.hashcode = "+d1.hashCode());
        System.out.println(d2);
        System.out.println("d2.hashcode = "+d2.hashCode());
        System.out.println("d1 == d2 : "+(d1==d2));
//        d2.setCode("02");
        System.out.println(d2);
        System.out.println("d2.hashcode = "+d2.hashCode());
        System.out.println("---------------");
        HashSet<DeliveryCode> hashSet = new HashSet<>();
        hashSet.add(d1);
        hashSet.add(d2);
        System.out.println(hashSet);
    }
}
