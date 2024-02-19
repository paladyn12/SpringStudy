package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto itemDto){
        Book findItem = (Book) itemRepository.fineOne(itemId);

        findItem.setId(itemDto.getId());
        findItem.setName(itemDto.getName());
        findItem.setPrice(itemDto.getPrice()); //변경 감지 기능
        findItem.setStockQuantity(itemDto.getStockQuantity());
        findItem.setAuthor(itemDto.getAuthor());
        findItem.setIsbn(itemDto.getIsbn());

        //itemRepository.save(item); 이 문장 필요 없음
        //트랜잭션 내에서 엔티티를 다시 조회하여 변경할 값을 선택하고 트랜잭션 커밋 시점에서 변경 감지(Dirty Checking)
        //이를 통해 데이터베이스에 UPDATE SQL 실행

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.fineOne(id);
    }
}
