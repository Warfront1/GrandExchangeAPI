package scripts;


import org.tribot.api.General;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.tk.elitescripts.grandexchangeapi.inventory.GrandExchangeInventory;
import scripts.tk.elitescripts.grandexchangeapi.primaryscreen.ActiveOfferSlot;
import scripts.tk.elitescripts.grandexchangeapi.GrandExchange;



@ScriptManifest(authors = { "Warfront1" }, category = "Tools", name = "GE 2API Test")
public class GrandExchangeTest extends Script {

    @Override
    public void run() {
        General.sleep(5000);
        while(true){
//            System.out.println(new GrandExchange().getSellScreen().getiFaceComponent()[0].getParent().getIndex());
//            System.out.println("Enabled Empty offers"+new GrandExchange().getEnabledEmptyOffers().length);
//            System.out.println("Disabled Empty offers" + new GrandExchange().getDisabledEmptyOffers().length);
//            for (ActiveOfferSlot slot: new GrandExchange().getBuyOffers()){
//                System.out.println(slot.getOfferType());
//                System.out.println(slot.getItemID());
//                System.out.println(slot.getItemName());
//                System.out.println(slot.getItemPrice());
//                System.out.println(slot.isCompleted());
//                System.out.println("Can Collect "+ new GrandExchange().canCollect());
//            }
            System.out.println("Test");
            new GrandExchangeInventory().getInventoryItems(379)[0].click("Offer");
//            new GrandExchange().getBuyScreen().getManuallyEnterAmountButton().click("");
            System.out.println("END OF TEST");
                    General.sleep(5000);
        }
    }
}
