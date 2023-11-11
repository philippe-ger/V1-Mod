package bloodmachinemod.cards.rare;

import bloodmachinemod.cards.BaseCard;
import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.powers.IntoTheFirePower;
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

public class IntoTheFire extends BaseCard {
    public static final String ID = makeID(IntoTheFire.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.NONE,
            2
    );

    public IntoTheFire() {
        super(ID, info);
        setInnate(false,true);
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

        addToBot(new ApplyPowerAction(p,p,new IntoTheFirePower(p,1)));
        CardCrawlGame.music.dispose();
        AbstractDungeon.getCurrRoom().playBgmInstantly("ost_intothefire.ogg");
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new IntoTheFire();
    }
}