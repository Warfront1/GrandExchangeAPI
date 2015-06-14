package scripts.tk.elitescripts.grandexchangeapi.secondaryscreen;


import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSItem;

import java.util.ArrayList;

public class ItemSelection {
    private int interfaceMaster = 162;
    private int interfaceChild = 38;
    private RSInterfaceComponent[] components;
    private RSItem[] items;
    public ItemSelection(){
        RSInterfaceChild itemSelectionMaster = Interfaces.get(this.interfaceMaster, this.interfaceChild);
        if(itemSelectionMaster!=null){
            RSInterfaceComponent[] children = itemSelectionMaster.getChildren();
            if(children!=null){
                this.components=children;
                ArrayList<RSItem> returnArrayList = new ArrayList<RSItem>();
                for(RSInterfaceComponent individualComponent: components){
                    if(individualComponent.getComponentItem()!=-1){
                        RSItem newItem = new RSItem(individualComponent.getComponentIndex(), individualComponent.getComponentItem(), individualComponent.getComponentStack(), RSItem.TYPE.OTHER);
                        newItem.setArea(individualComponent.getAbsoluteBounds());
                        returnArrayList.add(newItem);
                    }
                }
                this.items = returnArrayList.toArray(new RSItem[returnArrayList.size()]);
            }
        }
    }
    public boolean isOpen(){
        if(this.components!=null && this.components.length>0){
            return !this.components[0].isHidden(true);
        }
        return false;
    }
    public RSItem[] getItems(){
        return this.items;
    }
    public RSItem getItem(int itemID){
        if(this.items!=null){
            for(RSItem i: this.items){
                if(i.getID()==itemID){
                    return i;
                }
            }
        }
        return null;
    }

}
