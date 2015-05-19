package scripts.tk.elitescripts.grandexchangeapi.inventory;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

import java.util.ArrayList;


public class GrandExchangeInventory {
    final int interfaceMasterID = 467;
    final int interfaceChildID = 0;
    RSItem[] inventoryItems;
    public GrandExchangeInventory(){
        ArrayList<RSItem> returnArrayList = new ArrayList<RSItem>();
        RSInterfaceChild inventoryIFace = Interfaces.get(this.interfaceMasterID, this.interfaceChildID);
        if(inventoryIFace != null) {
            RSInterfaceComponent[] children = inventoryIFace.getChildren();
            if (children != null) {
                for (RSInterfaceComponent child : children) {
                    String[] actions = child.getActions();
                    if (child.getComponentItem() != -1 && actions != null && actions.length > 0) {
                        RSItem newItem = new RSItem(child.getComponentIndex(), child.getComponentItem(), child.getComponentStack(), RSItem.TYPE.OTHER);
                        newItem.setArea(child.getAbsoluteBounds());
                        returnArrayList.add(newItem);
                    }
                }
            }
        }
        this.inventoryItems = returnArrayList.toArray(new RSItem[returnArrayList.size()]);
    }
    public RSItem[] getAllInventoryItems(){
        return this.inventoryItems;
    }
    public RSItem[] getInventoryItems(int itemID){
        ArrayList<RSItem> returnArrayList = new ArrayList<RSItem>();
        RSItem[] allItems = this.inventoryItems;
        if(allItems!=null){
            for(RSItem individualItem:allItems){
                if(individualItem.getID()==itemID){
                    returnArrayList.add(individualItem);
                }
            }
        }
        return returnArrayList.toArray(new RSItem[returnArrayList.size()]);
    }
    public RSItem[] getInventoryItems(String itemName){
        ArrayList<RSItem> returnArrayList = new ArrayList<RSItem>();
        RSItem[] allItems = this.inventoryItems;
        if(allItems!=null){
            for(RSItem individualItem:allItems){
                RSItemDefinition itemDefinition = RSItemDefinition.get(individualItem.getID());
                if(itemDefinition!=null){
                    String definitionitemName = itemDefinition.getName();
                    if(definitionitemName!=null){
                        if(definitionitemName.equalsIgnoreCase(itemName)){
                            returnArrayList.add(individualItem);
                        }
                    }
                }
            }
        }
        return returnArrayList.toArray(new RSItem[returnArrayList.size()]);
    }
}
