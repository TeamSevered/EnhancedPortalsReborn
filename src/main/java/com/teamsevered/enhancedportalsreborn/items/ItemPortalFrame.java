package com.teamsevered.enhancedportalsreborn.items;

//import enhancedportals.block.BlockFrame;
import com.teamsevered.enhancedportalsreborn.util.Localization;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemPortalFrame extends ItemBase
{
    public ItemPortalFrame(String name)
    {
        super(name);
        this.setMaxDamage(0);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    //@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        int damage = stack.getItemDamage();

        if (damage > 0)
        {
            list.add(Localization.get("block.portalFramePart"));
        }
    }

    @Override
    public int getMetadata(int metadata) {

        return metadata;
    }


    //todo @Override

}
