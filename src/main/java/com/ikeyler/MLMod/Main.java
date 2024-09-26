package com.ikeyler.MLMod;

import com.ikeyler.MLMod.message.MessageManager;
import com.ikeyler.MLMod.message.Messages;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    public static Minecraft mc = Minecraft.getMinecraft();
    public static final String prefix = "§8» §f";
    public static Messages MsgList = new Messages();
    public static Logger logger;
    public static MessageManager messageManager = new MessageManager();
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        MsgList.setMessages();
        Keybinds.register();
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new Events());
        MinecraftForge.EVENT_BUS.register(new Sounds());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Sounds.registerSounds();
    }
}
