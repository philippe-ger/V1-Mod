package bloodmachinemod.cards.uncommon;

import bloodmachinemod.cards.BaseCard;
import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.powers.CyberGrindPower;
import bloodmachinemod.powers.VersusPower;
import bloodmachinemod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;

import static bloodmachinemod.BloodMachineMod.makeID;

public class CyberGrind extends BaseCard {
    public static final String ID = makeID(CyberGrind.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2
    );

    private static final int MAGIC_NUM = 1;
    private static final int UPG_MAGIC_NUM = 1;


    public CyberGrind() {
        super(ID, info);
        setMagic(MAGIC_NUM, UPG_MAGIC_NUM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //remove other ost powers first
        if (p.hasPower(makeID("CyberGrindPower"))) {
            addToBot(new RemoveSpecificPowerAction(p, p, makeID("CyberGrindPower")));
            addToBot(new GainEnergyAction(2));
        }
        if (p.hasPower(makeID("VersusPower"))) {
            addToBot(new RemoveSpecificPowerAction(p, p, makeID("VersusPower")));
            addToBot(new GainEnergyAction(2));
        }
        if (p.hasPower(makeID("IntoTheFirePower"))) {
            addToBot(new RemoveSpecificPowerAction(p, p, makeID("IntoTheFirePower")));
            addToBot(new GainEnergyAction(2));
        }

        addToBot(new ApplyPowerAction(p,p,new CyberGrindPower(p,1,this.magicNumber)));
        CardCrawlGame.music.dispose();
        AbstractDungeon.getCurrRoom().playBgmInstantly("ost_cybergrind.ogg");
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CyberGrind();
    }
}