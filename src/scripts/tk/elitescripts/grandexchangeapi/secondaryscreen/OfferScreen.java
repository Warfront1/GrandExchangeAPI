package scripts.tk.elitescripts.grandexchangeapi.secondaryscreen;

import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSItemDefinition;

import java.util.ArrayList;

/**
 * Created by joe on 5/10/2015.
 */
public class OfferScreen {
    public enum screenType { SELL , BUY, NOOFFER};


    private OfferScreen.screenType screenType;
    private RSInterfaceComponent iFaceComponent[];
    private int itemID = -1;
    private RSInterfaceComponent plusOneButton;
    private RSInterfaceComponent plusTenButton;
    private RSInterfaceComponent plusOneHundredButton;
    private RSInterfaceComponent plusOneThousandButton;
    private RSInterfaceComponent manuallyEnterAmountButton;
    private RSInterfaceComponent markDown5PercentButton;
    private RSInterfaceComponent markGuidePriceButton;
    private RSInterfaceComponent manuallyEnterPriceButton;
    private RSInterfaceComponent markUp5PercentButton;

    public OfferScreen(RSInterfaceComponent[] iFaceComponent){
        this.iFaceComponent=iFaceComponent;
        for(RSInterfaceComponent individualComponent: this.iFaceComponent){
            String componentText = individualComponent.getText();
            if(componentText!=null){
                if(componentText.equals("No offer")){
                    this.screenType= screenType.NOOFFER;
                }
                else if(componentText.contains("Sell offer")){
                    this.screenType= screenType.SELL;
                }
                else if(componentText.contains("Buy offer")){
                    this.screenType= screenType.BUY;
                }
            }
            int componentItemID = individualComponent.getComponentItem();
            if(componentItemID != -1){
                this.itemID  = componentItemID;
            }
        }
        setButtons();
    }

    public int getItemID(){
        return this.itemID;
    }
//    public int getQuantity(){
//        if(this.itemID==-1) {
//            return 0;
//        }
//        else{
//
//        }
//    }
    public int getQuantity(){
        if(this.iFaceComponent!=null){
            for(RSInterfaceComponent individualComponent: this.iFaceComponent){
                String componentText = individualComponent.getText();
                if(componentText!=null){
                    componentText = componentText.replaceAll(",","");
                    if(!componentText.matches("^$")) {
                        if (componentText.matches("^([0-9]*)$")) {
                            return Integer.parseInt(componentText);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private void setButtons() {
        if (this.iFaceComponent != null) {
            for (RSInterfaceComponent individualComponent : this.iFaceComponent) {
                String[] actions = individualComponent.getActions();
                if(actions!=null){
                    for(String action: actions){
                        if(action.equals("+1")){
                            this.plusOneButton = individualComponent;
                        }
                        else if(action.equals("+10")){
                            this.plusTenButton = individualComponent;
                        }
                        else if(action.equals("+100")){
                            this.plusOneHundredButton= individualComponent;
                        }
                        else if(action.equals("+1k")){
                            this.plusOneThousandButton= individualComponent;
                        }
                        else if(action.equals("Enter quantity")){
                            this.manuallyEnterAmountButton= individualComponent;
                        }
                        else if(action.equals("-5%")){
                            this.markDown5PercentButton= individualComponent;
                        }
                        else if(action.equals("Guide price")){
                            this.markGuidePriceButton= individualComponent;
                        }
                        else if(action.equals("Enter price")){
                            this.manuallyEnterPriceButton= individualComponent;
                        }
                        else if(action.equals("+5%")){
                            this.markUp5PercentButton= individualComponent;
                        }
                    }
                }
            }
        }
    }
    public int getPricePerItem(){
        int coinvalueone = -1;
        int coinvaluetwo = -1;
        if(this.iFaceComponent!=null){
            for(RSInterfaceComponent individualComponent: this.iFaceComponent){
                String componentText = individualComponent.getText();
                if(componentText!=null){
                    componentText = componentText.replaceAll(",","");
                    if(componentText.contains("coins")){
                        componentText = componentText.replaceAll(" coins","");
                        if(coinvalueone==-1) {
                            coinvalueone = Integer.parseInt(componentText);
                        }
                        else{
                            coinvaluetwo = Integer.parseInt(componentText);
                        }
                    }
                }
            }
        }
        return Math.min(coinvalueone,coinvaluetwo);
    }
    public int getTotalCost(){
        int coinvalueone = -1;
        int coinvaluetwo = -1;
        if(this.iFaceComponent!=null){
            for(RSInterfaceComponent individualComponent: this.iFaceComponent){
                String componentText = individualComponent.getText();
                if(componentText!=null){
                    componentText = componentText.replaceAll(",","");
                    if(componentText.contains("coins")){
                        componentText = componentText.replaceAll(" coins","");
                        if(coinvalueone==-1) {
                            coinvalueone = Integer.parseInt(componentText);
                        }
                        else{
                            coinvaluetwo = Integer.parseInt(componentText);
                        }
                    }
                }
            }
        }
        return Math.max(coinvalueone,coinvaluetwo);
    }
    public String getItemName(){
        RSItemDefinition itemDef  = RSItemDefinition.get(this.itemID);
        if(itemDef !=null){
            String itemName = itemDef.getName();
            if(itemName!=null){
                return itemName;
            }
        }
        return null;
    }
    public OfferScreen.screenType getScreenType() {
        return this.screenType;
    }

    public RSInterfaceComponent[] getiFaceComponent(){
        return this.iFaceComponent;
    }

    public RSInterfaceComponent getMarkUp5PercentButton() {
        return this.markUp5PercentButton;
    }

    public RSInterfaceComponent getManuallyEnterPriceButton() {
        return this.manuallyEnterPriceButton;
    }

    public RSInterfaceComponent getMarkGuidePriceButton() {
        return this.markGuidePriceButton;
    }

    public RSInterfaceComponent getPlusOneButton() {
        return this.plusOneButton;
    }

    public RSInterfaceComponent getPlusTenButton() {
        return this.plusTenButton;
    }

    public RSInterfaceComponent getPlusOneHundredButton() {
        return this.plusOneHundredButton;
    }

    public RSInterfaceComponent getPlusOneThousandButton() {
        return this.plusOneThousandButton;
    }

    public RSInterfaceComponent getManuallyEnterAmountButton() {
        return this.manuallyEnterAmountButton;
    }

    public RSInterfaceComponent getMarkDown5PercentButton() {
        return this.markDown5PercentButton;
    }

}
