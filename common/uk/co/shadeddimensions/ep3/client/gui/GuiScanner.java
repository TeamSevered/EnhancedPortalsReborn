package uk.co.shadeddimensions.ep3.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import uk.co.shadeddimensions.ep3.container.ContainerScanner;
import uk.co.shadeddimensions.ep3.container.InventoryScanner;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.ItemEnergyContainer;
import cofh.gui.GuiBase;
import cofh.gui.element.ElementEnergyStored;

public class GuiScanner extends GuiBase
{
    ItemStack stack;
    
    public GuiScanner(InventoryScanner scanner, EntityPlayer player, ItemStack s)
    {
        super(new ContainerScanner(scanner, player, s), new ResourceLocation("enhancedportals", "textures/gui/scanner.png"));
        stack = s;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        super.drawGuiContainerForegroundLayer(x, y);
        
        fontRenderer.drawString("Scanner", 7, 7, 0x404040);
        fontRenderer.drawString("Inventory", 7, 70, 0x404040);
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
        
        EnergyStorage storage = new EnergyStorage(2000, 250, 250);
        storage.setEnergyStored(((ItemEnergyContainer) stack.getItem()).getEnergyStored(stack));
        addElement(new ElementEnergyStored(this, xSize - 22, 22, storage));
    }
}