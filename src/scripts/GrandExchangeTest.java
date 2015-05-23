package scripts;


import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.tk.elitescripts.grandexchangeapi.inventory.GrandExchangeInventory;
import scripts.tk.elitescripts.grandexchangeapi.primaryscreen.ActiveOfferSlot;
import scripts.tk.elitescripts.grandexchangeapi.GrandExchange;
import scripts.tk.elitescripts.grandexchangeapi.primaryscreen.EmptyOfferSlot;
import scripts.tk.elitescripts.grandexchangeapi.secondaryscreen.ItemSelection;
import scripts.tk.elitescripts.grandexchangeapi.secondaryscreen.OfferScreen;
import sun.invoke.empty.Empty;


@ScriptManifest(authors = { "Warfront1" }, category = "Tools", name = "GE 2API Test")
public class GrandExchangeTest extends Script {

    @Override
    public void run() {
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
            buyItem(7936);
//            new GrandExchangeInventory().getInventoryItems(379)[0].click("Offer");
//            new GrandExchange().getBuyScreen().getManuallyEnterAmountButton().click("");
            System.out.println("END OF TEST");
                    General.sleep(1000);
        }
    }
    public void buyItem(int itemID){
        GrandExchange GE = new GrandExchange();
        if(GE.isOpen()) {
            if (GE.canCollect()) {
                GE.clickCollectButton();
            }
            else{
                OfferScreen buyScreen = GE.getBuyScreen();
                if(buyScreen!=null && buyScreen.isOpen()) {
                    ItemSelection itemSelectionMenu = new ItemSelection();
                    if(itemSelectionMenu != null && itemSelectionMenu.isOpen()){
                        RSItem itemToBuy = itemSelectionMenu.getItem(itemID);
                        if(itemToBuy!=null){
                            itemToBuy.click("");
                        }
                        else{
                            Keyboard.typeSend(getItemNameFromID(itemID));
                            General.sleep(300,500);
                        }
                    }
                    else if(buyScreen.getItemID()==itemID){
                        buyScreen.clickConfirmButton();
                    }

                }
                else{
                    EmptyOfferSlot[] AllEmptySlots = GE.getEnabledEmptyOffers();
                    if (AllEmptySlots != null && AllEmptySlots.length > 0) {
                        AllEmptySlots[0].clickBuyOffer();
                    }
                }
            }
        }
        else{
            GE.openGrandExchange();
        }
    }
    public String getItemNameFromID(int itemID){
        RSItemDefinition itemDef  = RSItemDefinition.get(itemID);
        if(itemDef !=null){
            String itemName = itemDef.getName();
            if(itemName!=null){
                return itemName;
            }
        }
        return "";
    }

}
