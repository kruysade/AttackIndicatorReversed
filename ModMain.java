package com.tonpseudo.attackindicator;

import net.fabricmc.api.ClientModInitializer;

public class ModMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Attack Indicator Reversed mod loaded!");
    }
}
