package bloodmachinemod.cards.uncommon;

import bloodmachinemod.cards.BaseCard;
import bloodmachinemod.character.BloodMachine;
import bloodmachinemod.powers.CoinPower;
import bloodmachinemod.powers.VersusPower;
import bloodmachinemod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Versus extends BaseCard {
    public static final String ID = makeID(Versus.class.getSimpleName());
    private static final CardStats info = new CardStats(
            BloodMachine.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2
    );

    private static final int MAGIC_NUM = 25;
    private static final int UPG_MAGIC= 8;

    public Versus() {
        super(ID, info);
        setMagic(MAGIC_NUM,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new ApplyPowerAction(p,p,new VersusPower(p,1,this.magicNumber)));
            AbstractDungeon.getCurrRoom().playBgmInstantly("ost_versus.ogg");
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Versus();
    }
}