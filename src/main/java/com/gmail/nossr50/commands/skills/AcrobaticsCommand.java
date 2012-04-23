package com.gmail.nossr50.commands.skills;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.nossr50.Users;
import com.gmail.nossr50.commands.CommandHelper;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.datatypes.SkillType;
import com.gmail.nossr50.locale.mcLocale;
import com.gmail.nossr50.util.Page;

public class AcrobaticsCommand implements CommandExecutor {
    private float skillValue;
    private String dodgeChance;
    private String rollChance;
    private String gracefulRollChance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (CommandHelper.noConsoleUsage(sender)) {
            return true;
        }

        if (CommandHelper.noCommandPermissions(sender, "mcmmo.skills.acrobatics")) {
            return true;
        }

        Player player = (Player) sender;
        PlayerProfile PP = Users.getProfile(player);

        skillValue = (float) PP.getSkillLevel(SkillType.ACROBATICS);
        dataCalculations(skillValue);

        player.sendMessage(mcLocale.getString("Skills.Header", new Object[] { mcLocale.getString("Acrobatics.SkillName") }));
        player.sendMessage(mcLocale.getString("Commands.XPGain", new Object[] { mcLocale.getString("Commands.XPGain.Acrobatics") }));
        player.sendMessage(mcLocale.getString("Effects.Level", new Object[] { PP.getSkillLevel(SkillType.ACROBATICS), PP.getSkillXpLevel(SkillType.ACROBATICS), PP.getXpToLevel(SkillType.ACROBATICS) }));

        player.sendMessage(mcLocale.getString("Skills.Header", new Object[] { mcLocale.getString("Effects.Effects") }));
        player.sendMessage(mcLocale.getString("Effects.Template", new Object[] { mcLocale.getString("Acrobatics.Effect.0"), mcLocale.getString("Acrobatics.Effect.1") }));
        player.sendMessage(mcLocale.getString("Effects.Template", new Object[] { mcLocale.getString("Acrobatics.Effect.2"), mcLocale.getString("Acrobatics.Effect.3") }));
        player.sendMessage(mcLocale.getString("Effects.Template", new Object[] { mcLocale.getString("Acrobatics.Effect.4"), mcLocale.getString("Acrobatics.Effect.5") }));

        player.sendMessage(mcLocale.getString("Skills.Header", new Object[] { mcLocale.getString("Commands.Stats.Self") }));
        player.sendMessage(mcLocale.getString("Acrobatics.Roll.Chance", new Object[] { rollChance }));
        player.sendMessage(mcLocale.getString("Acrobatics.Roll.GraceChance", new Object[] { gracefulRollChance }));
        player.sendMessage(mcLocale.getString("Acrobatics.DodgeChance", new Object[] { dodgeChance }));

        Page.grabGuidePageForSkill(SkillType.ACROBATICS, player, args);

        return true;
    }

    private void dataCalculations(float skillValue) {
        if (skillValue >= 1000) {
            dodgeChance = "20";
            rollChance = "100";
            gracefulRollChance = "100";
        }
        else if (skillValue >= 800) {
            dodgeChance = "20";
            rollChance = String.valueOf(skillValue / 10);
            gracefulRollChance = "100";
        }
        else if (skillValue >= 500) {
            dodgeChance = String.valueOf(skillValue / 40);
            rollChance = String.valueOf(skillValue / 10);
            gracefulRollChance = "100";
        }
        else {
            dodgeChance = String.valueOf(skillValue / 40);
            rollChance = String.valueOf(skillValue / 10);
            gracefulRollChance = String.valueOf(skillValue / 5);
        }
    }
}