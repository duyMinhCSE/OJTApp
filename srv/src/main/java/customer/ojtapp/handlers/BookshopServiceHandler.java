package customer.bookshop.handlers;

import org.springframework.stereotype.Component;

import com.sap.cds.Result;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;

import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;

@Component
@ServiceName("bookshopService")
public class BookshopServiceHandler implements EventHandler {

    @After(event = CqnService.EVENT_READ, entity = "bookshopService.Books")
    public void onRead(CdsReadEventContext context) {
        Result result = context.getResult();
        //result.forEach(r -> System.out.println(r.get("title")));
        result.forEach(r -> {
            if (((Integer) r.get("stock")) > 111)
                r.put("title", ((String) r.get("title")).concat(" -- Discount"));
        });
    }
}
