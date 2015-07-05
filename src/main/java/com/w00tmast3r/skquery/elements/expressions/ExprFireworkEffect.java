package com.w00tmast3r.skquery.elements.expressions;

import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.w00tmast3r.skquery.util.Collect;
import org.bukkit.event.Event;
import ch.njol.skript.util.Color;
import ch.njol.skript.lang.Expression;
import com.w00tmast3r.skquery.api.Patterns;
import org.bukkit.FireworkEffect;
import ch.njol.skript.lang.util.SimpleExpression;

@Patterns({ "(1¦|2¦flickering |3¦trailing |4¦flickering trailing |5¦trailing flickering )%fireworktype% firework [effect] colored %colors%", "(1¦|2¦flickering |3¦trailing |4¦flickering trailing |5¦trailing flickering )%fireworktype% firework [effect] colored %colors% fad(e|ing) [to] %colors%" })
public class ExprFireworkEffect extends SimpleExpression<FireworkEffect>
{
    private Expression<FireworkEffect.Type> type;
    private Expression<Color> color;
    private Expression<Color> fade;
    private boolean flicker;
    private boolean trail;
    private boolean hasFade;
    
    protected FireworkEffect[] get(final Event event) {
        final FireworkEffect.Type t = (FireworkEffect.Type)this.type.getSingle(event);
        if (t == null) {
            return null;
        }
        final FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.with(t);
        for (final Color c : (Color[])this.color.getAll(event)) {
            builder.withColor(c.getBukkitColor());
        }
        if (this.hasFade) {
            for (final Color c : (Color[])this.fade.getAll(event)) {
                builder.withFade(c.getBukkitColor());
            }
        }
        builder.flicker(this.flicker);
        builder.trail(this.trail);
        return Collect.asArray(builder.build());
    }
    
    public boolean isSingle() {
        return true;
    }
    
    public Class<? extends FireworkEffect> getReturnType() {
        return FireworkEffect.class;
    }
    
    public String toString(final Event event, final boolean b) {
        return "ssss pop";
    }
    
    public boolean init(final Expression<?>[] expressions, final int i, final Kleenean kleenean, final SkriptParser.ParseResult parseResult) {
        this.flicker = (parseResult.mark == 2 || parseResult.mark > 3);
        this.trail = (parseResult.mark >= 3);
        this.hasFade = (i == 1);
        this.type = (Expression<FireworkEffect.Type>)expressions[0];
        this.color = (Expression<Color>)expressions[1];
        if (this.hasFade) {
            this.fade = (Expression<Color>)expressions[2];
        }
        return true;
    }
}
