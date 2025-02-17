package fi.dy.masa.malilib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fi.dy.masa.malilib.network.ClientPacketChannelHandlerImpl;
import fi.dy.masa.malilib.registry.Registry;

@Mixin(net.minecraft.client.network.NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient
{
    @Inject(method = "handleCustomPayload", at = @At("RETURN"))
    private void onCustomPayload(net.minecraft.network.play.server.SPacketCustomPayload packet, CallbackInfo ci)
    {
        net.minecraft.client.network.NetHandlerPlayClient handler = (net.minecraft.client.network.NetHandlerPlayClient) (Object) this;
        ((ClientPacketChannelHandlerImpl) Registry.CLIENT_PACKET_CHANNEL_HANDLER).processPacketFromServer(packet, handler);
    }
}
