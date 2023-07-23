package org.iridescent.nightmare.mobs.legendary.MobGiant;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.animal.EntityAnimal;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import net.minecraft.world.entity.monster.EntityPhantom;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.npc.EntityVillagerAbstract;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;

public class EntityGiant extends EntityGiantZombie {
    //There are no issues here, ignore the editor's error warnings.
    public EntityGiant(EntityTypes<? extends EntityGiantZombie> var0, World var1) {
        super(var0, var1);
    }

    public EntityGiant(Location spawnLoc) {
        this(EntityTypes.R, ((CraftWorld) spawnLoc.getWorld()).getHandle());
        a(spawnLoc.getX(), spawnLoc.getY(), spawnLoc.getZ(), spawnLoc.getYaw(), spawnLoc.getPitch());

    }

    @Override
    protected void x() {
        this.bO.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.bO.a(8, new PathfinderGoalRandomLookaround(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.bO.a(2, new PathfinderGoalMeleeAttack(this, 1.0, false));
        this.bO.a(7, new PathfinderGoalRandomStrollLand(this, 1.0));
        this.bP.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[]{EntityPigZombie.class}));
        this.bP.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        if (this.dI().spigotConfig.zombieAggressiveTowardsVillager) {
            this.bP.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityVillagerAbstract.class, false));
        }
        this.bP.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityPhantom.class, true));
        this.bP.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityAnimal.class, true));
        this.bP.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    @Override
    public boolean f_() {
        return super.f_();
    }

    public boolean j(ItemStack itemstack) {
        return itemstack.a(Items.qd) && this.h_() && this.bM() ? false : super.j(itemstack);
    }

    public boolean k(ItemStack itemstack) {
        return itemstack.a(Items.qs) ? false : super.k(itemstack);
    }
}
