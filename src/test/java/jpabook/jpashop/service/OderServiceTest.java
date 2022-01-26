
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class OrderServiceTest {
    
    
    @Autonwired EntityManager em;
    @Autonwired OrderService orderService;
    @Autonwired OrderRepository orderRepository;
    
    
    @Test
    public void 상품주문() throws Exception {
        
        //given
        Member member  = new Member();
        member.setName("회원1");
        member.setAddress(new Address( "서울", "강가", "123-123"));
        em.persist(member);
        
        book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        
        int orderCount =2;
        
        //when
        Long orderId = orerService.order(member.getId() , book.getId(), orderCount);
        
        //then
        Order getOrder = orderRepository.findOne(orderId);
        
        assertEquals("상품 주문시 상태는 order", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량 만큼 재고가 줄어야 한다.", 8 , getOrder.getStockQuantity());
        
    }
    
    
    @Test(expected = NotEnoughtStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        
        //given
        /*
        Member member  = new Member();
        member.setName("회원1");
        member.setAddress(new Address( "서울", "강가", "123-123"));
        em.persist(member);
        
        선택해서 ctrl + alt + M
        */
        //when
        
        //then
    }
    
    
    @Test
    public void 주문취소() throws Exception {
        //given
        
        //when
        
        //then
    }
    
    
    
}