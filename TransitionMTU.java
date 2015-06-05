
public class TransitionMTU {
	final static int alpha = 36; // nombre de symboles
	
	final static int cNULL		= -123456789;
	final static int eNULL		= -123456789;
	
	final static int cCOPIE		= -123456789;
	final static int eCOPIE		= -123456789;

	
	final static int B			= -1; // symbole blanc
	final static int cB			= 35; // index blanc
	final static int c0 		= 0;
	final static int c1 		= 1;
	final static int c2 		= 2;
	final static int cZ 		= 3;
	final static int cU 		= 4;
	final static int cEUtil 	= 5;
	final static int cENUtil 	= 6;
	final static int cSUtil 	= 7;
	final static int cSNUtil	= 8;
	final static int cIs		= 9;
	final static int cIe		= 10;
	final static int cFTs		= 11;
	final static int cFTe		= 12;
	final static int cECs		= 13;
	final static int cECe		= 14;
	final static int cESs		= 15;
	final static int cESe		= 16;
	final static int cTs		= 17;
	final static int cTe		= 18;
	final static int cTEs		= 19;
	final static int cTEe		= 20;
	final static int cTEDs		= 21;
	final static int cTEDe		= 22;
	final static int cTDs		= 23;
	final static int cTDe		= 24;
	final static int cTSs		= 25;
	final static int cTSe		= 26;
	final static int cTNEs		= 27;
	final static int cTNEe		= 28;
	final static int cTNSs		= 29;
	final static int cTNSe		= 30;
	final static int cTMs		= 31;
	final static int cTMe		= 32;
	final static int cSs		= 33;
	final static int cSe		= 34;
	
	final static int mGAUCHE	= 0;
	final static int mSURPLACE	= 1;
	final static int mDROITE	= 2;
	
	final static int eINIT		= 123456789;
	final static int eSTOP		= 987654321;
	final static int eOUT		= 111111111;
	
	static TableDeTransition DIEU;
	
	static Transition STOP;
	
	static void probleme1(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition trans1 = Dieu.newTransition();
		Transition trans2 = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition trans3 = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition trans4 = Dieu.newTransition();
		Transition trans5 = Dieu.newTransition();
		
		IN.definition(cECs, trans1, cECs, mDROITE);
		
		trans1.definition(c1, trans2, c1, mDROITE);
		trans1.definition(c0, trans3, c0, mDROITE);
		
		trans2.definition(cECe, trans4, cECe, mDROITE);
		
		trans3.definition(cECe, trans5, cECe, mDROITE);

		trans4.definition(c0, Dieu.transitionSTOP(), c1, mSURPLACE);

		trans5.definition(c0, OUT, c0, mSURPLACE);
	}
	
	static void probleme2(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition etatcourant = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition symbole = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		
		IN.definition(cECs, etatcourant, cECs, mSURPLACE);

		
		probleme2_spb1(Dieu, etatcourant, symbole);
		probleme2_spb2(Dieu, symbole, OUT);
	}
	
	static void probleme2_spb2(TableDeTransition Dieu, Transition IN, Transition OUT) {
		/* Rechercher le symbole sous la tête de lecture dans les définitions */
		Transition remplacerUTIL = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerSurECe = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition IOdroite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition droite = Dieu.newTransition(IOdroite, cCOPIE, mDROITE);
		Transition allerTeteDroite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTeteGauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		IN.definition(cEUtil, remplacerUTIL, cEUtil, mDROITE);
		
		remplacerUTIL.definition(cSNUtil, remplacerUTIL, cSUtil, mDROITE);
		remplacerUTIL.definition(cTe, allerSurECe, cTe, mDROITE);
		
		allerSurECe.definition(cECe, droite, cECe, mDROITE);
		
		IOdroite.definition(c0, allerTeteGauche, c0, mGAUCHE);
		IOdroite.definition(c1, allerTeteDroite, c0, mDROITE);
		
		
		Transition compTete_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerAuProchSymbDroite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition comp1_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comp0_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireU_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireZ_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireSUtil_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireSNUtil_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerFTs_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);

		allerFTs_droite.definition(cFTs, OUT, cFTs, mSURPLACE);
		
		allerTeteDroite.definition(cSe, compTete_droite, cSe, mDROITE);
		allerTeteDroite.definition(cIe, compTete_droite, cIe, mDROITE);
		
		compTete_droite.definition(c0, allerTeteDroite, c0, mDROITE);
		compTete_droite.definition(c1, allerAuProchSymbDroite, c1, mDROITE);

		allerAuProchSymbDroite.definition(c1, comp1_droite, cU, mGAUCHE);
		allerAuProchSymbDroite.definition(c0, comp0_droite, cZ, mGAUCHE);
		allerAuProchSymbDroite.definition(cSe, allerFTs_droite, cSe, mGAUCHE);
		
		comp1_droite.definition(cSUtil, ecrireU_droite, cSUtil, mDROITE);
		comp1_droite.definition(cFTs, allerTeteDroite, cFTs, mDROITE);
		
		ecrireU_droite.definition(c1, ecrireSUtil_droite, cU, mGAUCHE);
		ecrireU_droite.definition(c0, ecrireSNUtil_droite, c0, mGAUCHE);
		
		comp0_droite.definition(cSUtil, ecrireZ_droite, cSUtil, mDROITE);
		comp0_droite.definition(cFTs, allerTeteDroite, cFTs, mDROITE);
		
		ecrireZ_droite.definition(c0, ecrireSUtil_droite, cZ, mGAUCHE);
		ecrireZ_droite.definition(c1, ecrireSNUtil_droite, c1, mGAUCHE);

		
		ecrireSUtil_droite.definition(cSUtil, comp1_droite, cSUtil, mGAUCHE);
		
		ecrireSNUtil_droite.definition(cSUtil, comp1_droite, cSNUtil, mGAUCHE);
		
		
		Transition compTete_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerAuProchSymbGauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comp1_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition comp0_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireU_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireZ_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireSUtil_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireSNUtil_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerFTs_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);

		allerFTs_gauche.definition(cFTs, OUT, cFTs, mSURPLACE);
		
		allerTeteGauche.definition(cSs, compTete_gauche, cSs, mGAUCHE);
		
		compTete_gauche.definition(c0, allerTeteGauche, c0, mGAUCHE);
		compTete_gauche.definition(c1, allerAuProchSymbGauche, c1, mDROITE);

		allerAuProchSymbGauche.definition(c1, comp1_gauche, cU, mDROITE);
		allerAuProchSymbGauche.definition(c0, comp0_gauche, cZ, mDROITE);
		allerAuProchSymbGauche.definition(cSe, allerFTs_gauche, cSe, mDROITE);
		
		comp1_gauche.definition(cSUtil, ecrireU_gauche, cSUtil, mDROITE);
		comp1_gauche.definition(cFTe, allerTeteGauche, cFTe, mGAUCHE);
		
		ecrireU_gauche.definition(c1, ecrireSUtil_gauche, cU, mGAUCHE);
		ecrireU_gauche.definition(c0, ecrireSNUtil_gauche, c0, mGAUCHE);
		
		comp0_gauche.definition(cSUtil, ecrireZ_gauche, cSUtil, mDROITE);
		comp0_gauche.definition(cFTe, allerTeteGauche, cFTe, mGAUCHE);
		
		ecrireZ_gauche.definition(c0, ecrireSUtil_gauche, cZ, mGAUCHE);
		ecrireZ_gauche.definition(c1, ecrireSNUtil_gauche, c1, mGAUCHE);

		
		ecrireSUtil_gauche.definition(cSUtil, comp1_gauche, cSUtil, mDROITE);
		
		ecrireSNUtil_gauche.definition(cSUtil, comp1_gauche, cSNUtil, mDROITE);

	}
	
	static void probleme2_spb1(TableDeTransition Dieu, Transition IN, Transition OUT) {
		/* Rechercher l'état courant dans les définitions */
		Transition allerAuProchSymb = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition comp0 = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comp1 = Dieu.newTransition(null, cCOPIE, mDROITE);
		
		IN.definition(cECs, allerAuProchSymb, cECs, mDROITE);
		
		allerAuProchSymb.definition(c1, comp1, cU, mGAUCHE);
		allerAuProchSymb.definition(c0, comp0, cZ, mGAUCHE);
		allerAuProchSymb.definition(cECe, OUT, cECe, mSURPLACE);
		
		probleme2_spb1_comp0(Dieu, comp0, IN);
		probleme2_spb1_comp1(Dieu, comp1, IN);
	}
	
	static void probleme2_spb1_comp0(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition ecrireZ = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireEUtil = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireENUtil = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		
		IN.definition(cEUtil, ecrireZ, cEUtil, mSURPLACE);
		IN.definition(cFTs, OUT, cFTs, mDROITE);

		ecrireZ.definition(c0, ecrireEUtil, cZ, mGAUCHE);
		ecrireZ.definition(c1, ecrireENUtil, c1, mGAUCHE);
		
		ecrireEUtil.definition(cEUtil, IN, cEUtil, mGAUCHE);
		
		ecrireENUtil.definition(cEUtil, IN, cENUtil, mGAUCHE);
	}
	
	static void probleme2_spb1_comp1(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition ecrireU = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireEUtil = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireENUtil = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		
		IN.definition(cEUtil, ecrireU, cEUtil, mSURPLACE);
		IN.definition(cFTs, OUT, cFTs, mDROITE);

		ecrireU.definition(c1, ecrireEUtil, cU, mGAUCHE);
		ecrireU.definition(c0, ecrireENUtil, c0, mGAUCHE);
		
		ecrireEUtil.definition(cEUtil, IN, cEUtil, mGAUCHE);
		
		ecrireENUtil.definition(cEUtil, IN, cENUtil, mGAUCHE);
	}
	
	static void probleme3(TableDeTransition Dieu, Transition IN, Transition OUT) {
		/* Changer le caractère sous la tête */
		Transition lireIOdroite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerSUtil1_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerSUtil_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerSUtil_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);

		Transition allerTNSs_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTNSs_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerProchSymb_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete0_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition comptete0_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition ecrireZ_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete1_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition comptete1_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition ecrireU_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete3_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition comptete3_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition nettoyer_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerFTe_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);

		Transition allerProchSymb_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete0_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comptete0_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition ecrireZ_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete1_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comptete1_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition ecrireU_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete3_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comptete3_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition nettoyer_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerFTe_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);

		
		IN.definition(cIe, lireIOdroite, cIe, mGAUCHE); // -- mDROITE -> mGAUCHE
		
		lireIOdroite.definition(c1, allerSUtil_droite, c1, mGAUCHE); // -- mDROITE -> mGAUCHE
		lireIOdroite.definition(c0, allerSUtil1_gauche, c0, mGAUCHE);
		allerSUtil1_gauche.definition(cSUtil, allerSUtil_gauche, cSUtil, mSURPLACE); //-- mDROITE -> mSURPLACE
		
		allerSUtil_gauche.definition(cSUtil, allerTNSs_gauche, cSUtil, mDROITE);
		
		allerTNSs_gauche.definition(cTNSs, allerProchSymb_gauche, cTNSs, mDROITE);
		
		allerProchSymb_gauche.definition(c0, allerTete0_gauche, cZ, mGAUCHE);
		allerProchSymb_gauche.definition(c1, allerTete1_gauche, cU, mGAUCHE);
		allerProchSymb_gauche.definition(cTNSe, allerTete3_gauche, cTNSe, mGAUCHE);

		allerTete0_gauche.definition(cSs, comptete0_gauche, cSs, mGAUCHE);
		
		comptete0_gauche.definition(c1, ecrireZ_gauche, c1, mDROITE);
		comptete0_gauche.definition(c0, allerTete0_gauche, c0, mGAUCHE);
		
		ecrireZ_gauche.definition(c1, allerSUtil_gauche, cZ, mDROITE);
		ecrireZ_gauche.definition(c0, allerSUtil_gauche, cZ, mDROITE);
		
		allerTete1_gauche.definition(cSs, comptete1_gauche, cSs, mGAUCHE);
		
		comptete1_gauche.definition(c1, ecrireU_gauche, c1, mDROITE);
		comptete1_gauche.definition(c0, allerTete1_gauche, c0, mGAUCHE);
		
		ecrireU_gauche.definition(c1, allerSUtil_gauche, cU, mDROITE);
		ecrireU_gauche.definition(c0, allerSUtil_gauche, cU, mDROITE);
		
		allerTete3_gauche.definition(cSs, comptete3_gauche, cSs, mGAUCHE);

		comptete3_gauche.definition(c1, nettoyer_gauche, c1, mDROITE);
		comptete3_gauche.definition(c0, allerTete3_gauche, c0, mGAUCHE);
		
		nettoyer_gauche.definition(cU, nettoyer_gauche, c1, mDROITE);
		nettoyer_gauche.definition(cZ, nettoyer_gauche, c0, mDROITE);
		nettoyer_gauche.definition(cSe, allerFTe_gauche, cSe, mDROITE);

		allerFTe_gauche.definition(cFTe, OUT, cFTe, mGAUCHE);

		
/*-------- droite */
		allerSUtil_droite.definition(cSUtil, allerTNSs_droite, cSUtil, mDROITE);
		
		allerTNSs_droite.definition(cTNSs, allerProchSymb_droite, cTNSs, mDROITE);
		
		allerProchSymb_droite.definition(c0, allerTete0_droite, cZ, mDROITE);
		allerProchSymb_droite.definition(c1, allerTete1_droite, cU, mDROITE);
		allerProchSymb_droite.definition(cTNSe, allerTete3_droite, cTNSe, mDROITE);

		allerTete0_droite.definition(cSe, comptete0_droite, cSe, mDROITE);
		allerTete0_droite.definition(cIe, comptete0_droite, cIe, mDROITE);
		
		comptete0_droite.definition(c1, ecrireZ_droite, c1, mDROITE);
		comptete0_droite.definition(c0, allerTete0_droite, c0, mDROITE);
		
		ecrireZ_droite.definition(c1, allerSUtil_droite, cZ, mGAUCHE);
		ecrireZ_droite.definition(c0, allerSUtil_droite, cZ, mGAUCHE);
		
		allerTete1_droite.definition(cSe, comptete1_droite, cSe, mDROITE);
		allerTete1_droite.definition(cIe, comptete1_droite, cIe, mDROITE);
		
		comptete1_droite.definition(c1, ecrireU_droite, c1, mDROITE);
		comptete1_droite.definition(c0, allerTete1_droite, c0, mDROITE);
		
		ecrireU_droite.definition(c1, allerSUtil_droite, cU, mGAUCHE);
		ecrireU_droite.definition(c0, allerSUtil_droite, cU, mGAUCHE);
		
		allerTete3_droite.definition(cSe, comptete3_droite, cSe, mDROITE);
		allerTete3_droite.definition(cIe, comptete3_droite, cIe, mDROITE);

		comptete3_droite.definition(c1, nettoyer_droite, c1, mDROITE);
		comptete3_droite.definition(c0, allerTete3_droite, c0, mDROITE);
		
		nettoyer_droite.definition(cU, nettoyer_droite, c1, mDROITE);
		nettoyer_droite.definition(cZ, nettoyer_droite, c0, mDROITE);
		nettoyer_droite.definition(cSe, allerFTe_droite, cSe, mGAUCHE);

		allerFTe_droite.definition(cFTe, OUT, cFTe, mGAUCHE);
		
	}
	static void probleme4(TableDeTransition Dieu, Transition IN, Transition OUT) {
		/* déplacer la tête de lecture */
		Transition allerIOdroite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerSUtil_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerSUtil_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerMvm_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerMvm_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition lireMvm_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition lireMvm_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerTete0_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete1 = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete2_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerTete0_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerTete2_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition comptete0_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition comptete0_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		
		Transition mvm0_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition mvm0_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);

		Transition changerIOdroite0_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerIOgauche0_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition changerIOgauche0_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		
		
		Transition comptete2_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition comptete2_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition mvm2_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition mvm2_gauche = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition changerIOdroite2_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition allerIOdroite2_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition changerIOgauche2_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);

		
		Transition tete_droite = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition tete_gauche = Dieu.newTransition(null, cCOPIE, mSURPLACE);

		Transition allerIe_droite = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerIe_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerGAUCHE = Dieu.newTransition(tete_gauche, cCOPIE, mGAUCHE);
		Transition allerDROITE = Dieu.newTransition(tete_droite, cCOPIE, mDROITE);

		Transition creer_case_gauche = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition creer_case_droite = Dieu.newTransition(null, cCOPIE, mDROITE);
		
		IN.definition(cIe, allerIOdroite, cIe, mDROITE);
		
		allerIOdroite.definition(c1, allerSUtil_droite, c1, mGAUCHE);
		allerIOdroite.definition(c0, allerSUtil_gauche, c0, mGAUCHE);
		
		allerSUtil_droite.definition(cSUtil, allerMvm_droite, cSUtil, mDROITE);
		allerSUtil_gauche.definition(cSUtil, allerMvm_gauche, cSUtil, mDROITE);

		allerMvm_droite.definition(cTMs, lireMvm_droite, cTMs, mDROITE);
		allerMvm_gauche.definition(cTMs, lireMvm_gauche, cTMs, mDROITE);
		
		lireMvm_droite.definition(c0, allerTete0_droite, c0, mDROITE);
		lireMvm_droite.definition(c1, allerTete1, c1, mDROITE);
		lireMvm_droite.definition(c2, allerTete2_droite, c2, mDROITE);

		lireMvm_gauche.definition(c0, allerTete0_gauche, c0, mGAUCHE);
		lireMvm_gauche.definition(c1, allerTete1, c1, mDROITE);
		lireMvm_gauche.definition(c2, allerTete2_gauche, c2, mGAUCHE);

		allerTete0_droite.definition(cSe, comptete0_droite, cSe, mDROITE);
		allerTete0_droite.definition(cIe, comptete0_droite, cSe, mDROITE);
		
		allerTete0_gauche.definition(cSs, comptete0_gauche, cSs, mGAUCHE);
		
		comptete0_droite.definition(c1, mvm0_droite, c0, mGAUCHE);
		comptete0_droite.definition(c0, allerTete0_droite, c0, mDROITE);

		comptete0_gauche.definition(c1, mvm0_gauche, c0, mGAUCHE);
		comptete0_gauche.definition(c0, allerTete0_gauche, c0, mGAUCHE);
		
		/* attention passer du ruban droite au ruban gauche */
		mvm0_droite.definition(cSs, tete_droite, cSs, mGAUCHE);
		mvm0_droite.definition(cIe, changerIOdroite0_droite, cIe, mGAUCHE);
		changerIOdroite0_droite.definition(c1, allerIOgauche0_droite , c0, mGAUCHE);
		allerIOgauche0_droite.definition(cFTs, changerIOgauche0_droite, cFTs, mGAUCHE);
		changerIOgauche0_droite.definition(c0, allerGAUCHE, c1, mGAUCHE);

		/* attention a l'extrem ruban droite */
		mvm0_gauche.definition(cSs, tete_gauche, cSs, mGAUCHE);
		mvm0_gauche.definition(cB, creer_case_gauche, c2, mDROITE);

		
		
		/* mouvement 2 */
		allerTete2_droite.definition(cSe, comptete2_droite, cSe, mDROITE);
		allerTete2_droite.definition(cIe, comptete2_droite, cSe, mDROITE);
		
		allerTete2_gauche.definition(cSs, comptete2_gauche, cSs, mGAUCHE);
		
		comptete2_droite.definition(c1, mvm2_droite, c0, mDROITE);
		comptete2_droite.definition(c0, allerTete2_droite, c0, mDROITE);

		comptete2_gauche.definition(c1, mvm2_gauche, c0, mDROITE);
		comptete2_gauche.definition(c0, allerTete2_gauche, c0, mGAUCHE);
		
		/* attention a l'extrem ruban droite */
		mvm2_droite.definition(cSe, tete_droite, cSe, mDROITE);
		mvm2_droite.definition(cB, creer_case_droite, c2, mGAUCHE);
		
		/* attention passer du ruban gauche au ruban droit */
		mvm2_gauche.definition(cSe, tete_gauche, cSe, mGAUCHE);
		mvm2_gauche.definition(cIs, changerIOgauche2_gauche, cIs, mDROITE);
		changerIOgauche2_gauche.definition(c1, allerIOdroite2_gauche , c0, mDROITE);
		allerIOdroite2_gauche.definition(cIe, changerIOdroite2_gauche, cIe, mGAUCHE);
		changerIOdroite2_gauche.definition(c0, allerDROITE, c1, mDROITE);

		/* fin mouvement 2 */

		tete_droite.definition(c0, allerIe_droite, c1, mGAUCHE);
		
		tete_gauche.definition(c0, allerIe_gauche, c1, mDROITE);
		
		allerIe_droite.definition(cIe, OUT, cIe, mSURPLACE);
		allerIe_gauche.definition(cIe, OUT, cIe, mSURPLACE);
		allerTete1.definition(cIe, OUT, cIe, mSURPLACE);
		
		creer_case_gauche(Dieu, creer_case_gauche, allerIe_gauche);
		creer_case_droite(Dieu, creer_case_droite, allerIe_droite);

	}
	
	static void creer_case_droite(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition allerProchainSymb = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrire0 = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition nettoyer = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireSs = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireSigne = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition ecrireSe = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrire1 = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		
		IN.definition(cB, allerProchainSymb, c2, mGAUCHE);
		
		allerProchainSymb.definition(c0, ecrire0, cZ, mDROITE);
		allerProchainSymb.definition(c1, ecrire0, cU, mDROITE);
		allerProchainSymb.definition(cSs, nettoyer, cSs, mDROITE);
		
		ecrire0.definition(cB, allerProchainSymb, cZ, mGAUCHE);
		
		nettoyer.definition(cZ, nettoyer, c0, mDROITE);
		nettoyer.definition(cU, nettoyer, c1, mDROITE);
		nettoyer.definition(c2, ecrireSs, c1, mDROITE);
		ecrireSs.definition(c2, ecrireSigne, cSs, mDROITE);
		ecrireSigne.definition(cZ, ecrireSe, c1, mDROITE);
		ecrireSe.definition(cZ, ecrireSe, c0, mDROITE);
		ecrireSe.definition(cB, ecrire1, cSe, mGAUCHE);
		ecrire1.definition(c0, OUT, c1, mGAUCHE);
	}
	

	static void creer_case_gauche(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition allerProchainSymb = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrire0 = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition nettoyer = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireSe = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrireSigne = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		Transition ecrireSs = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition ecrire1 = Dieu.newTransition(null, cCOPIE, mSURPLACE);
		
		IN.definition(cSs, allerProchainSymb, cSs, mGAUCHE);
		
		allerProchainSymb.definition(c0, ecrire0, cZ, mGAUCHE);
		allerProchainSymb.definition(c1, ecrire0, cU, mGAUCHE);
		allerProchainSymb.definition(cSe, nettoyer, cSe, mGAUCHE);
		
		ecrire0.definition(cB, IN, cZ, mDROITE);
		
		nettoyer.definition(cZ, nettoyer, c0, mGAUCHE);
		nettoyer.definition(cU, nettoyer, c1, mGAUCHE);
		nettoyer.definition(c2, ecrireSe, c1, mGAUCHE);
		
		ecrireSe.definition(c2, ecrire1, cSs, mGAUCHE);
		ecrire1.definition(c0, ecrireSs, c1, mGAUCHE);
		ecrireSs.definition(cZ, ecrireSe, c0, mGAUCHE);
		ecrireSs.definition(cB, ecrire1, cSs, mDROITE);
		ecrireSigne.definition(cZ, OUT, c1, mDROITE);

	}
	
	static void probleme5(TableDeTransition Dieu, Transition IN, Transition OUT) {
		
		Transition allerTNEs = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition allerAuProchainSymb = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrire1 = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrire0 = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireU = Dieu.newTransition(null, cCOPIE, mDROITE);
		Transition ecrireZ = Dieu.newTransition(null, cCOPIE, mDROITE);
		
		IN.definition(cSUtil, allerTNEs, cSUtil, mDROITE);
		
		allerTNEs.definition(cTNEs, allerAuProchainSymb, cTNEs, mDROITE);
		
		allerAuProchainSymb.definition(c1, ecrire1, cU, mDROITE);
		allerAuProchainSymb.definition(c0, ecrire0, cZ, mDROITE);
		allerAuProchainSymb.definition(cTNEe, OUT, cTNEe, mSURPLACE);
		
		ecrire1.definition(cECs, ecrireU, cECs, mDROITE);
		
		ecrireU.definition(c1, IN, cU, mGAUCHE);
		ecrireU.definition(c0, IN, cU, mGAUCHE);
		
		ecrire0.definition(cECs, ecrireZ, cECs, mDROITE);
		
		ecrireZ.definition(c1, IN, cZ, mGAUCHE);
		ecrireZ.definition(c0, IN, cZ, mGAUCHE);

	}
	static void probleme6(TableDeTransition Dieu, Transition IN, Transition OUT) {
		Transition nettoyer = Dieu.newTransition(null, cCOPIE, mGAUCHE);
		Transition allerECs = Dieu.newTransition(null, cCOPIE, mDROITE);
		IN.definition(cIe, nettoyer, cIe, mGAUCHE);
		
		nettoyer.definition(cU, nettoyer, c1, mGAUCHE);
		nettoyer.definition(cZ, nettoyer, c0, mGAUCHE);
		nettoyer.definition(cENUtil, nettoyer, cEUtil, mGAUCHE);
		nettoyer.definition(cSUtil, nettoyer, cSNUtil, mGAUCHE);
		nettoyer.definition(cIs, allerECs, cIs, mDROITE);

		allerECs.definition(cECs, OUT, cECs, mSURPLACE);
	}

	public static boolean[] getSTOP() {
		boolean[] b = new boolean[131];
		b[0] = true;
		return b;
	}
	
	
   /* public static void main(String [] args) {*/
	public static int[][][] getFT() {
    	TableDeTransition Dieu = new TableDeTransition();

    	Transition pb1 = Dieu.newTransition();
    	pb1.setComment("etat initial");
    	Transition pb2 = Dieu.newTransition(null, cCOPIE, mGAUCHE);
    	Transition pb3 = Dieu.newTransition(null, cCOPIE, mDROITE);
    	Transition pb4 = Dieu.newTransition();
    	Transition pb5 = Dieu.newTransition(null, cCOPIE, mGAUCHE);
    	Transition pb6 = Dieu.newTransition(null, cCOPIE, mDROITE);
    	
    	probleme1(Dieu, pb1, pb2);
    	probleme2(Dieu, pb2, pb3);
    	probleme3(Dieu, pb3, pb4);
    	probleme4(Dieu, pb4, pb5);
    	probleme5(Dieu, pb5, pb6);
    	probleme6(Dieu, pb6, pb1);
    	
    	/*System.out.print(Dieu.toString());
    	 *
    	 */
    	return Dieu.toTAB();
     }
    public static void main(String [] args) {
    	TableDeTransition Dieu = new TableDeTransition();

    	Transition pb1 = Dieu.newTransition();
    	pb1.setComment("etat initial");
    	Transition pb2 = Dieu.newTransition(null, cCOPIE, mGAUCHE);
    	Transition pb3 = Dieu.newTransition(null, cCOPIE, mDROITE);
    	Transition pb4 = Dieu.newTransition();
    	Transition pb5 = Dieu.newTransition(null, cCOPIE, mGAUCHE);
    	Transition pb6 = Dieu.newTransition(null, cCOPIE, mDROITE);
    	
    	probleme1(Dieu, pb1, pb2);
    	probleme2(Dieu, pb2, pb3);
    	probleme3(Dieu, pb3, pb4);
    	probleme4(Dieu, pb4, pb5);
    	probleme5(Dieu, pb5, pb6);
    	probleme6(Dieu, pb6, pb1);
    	
    	/*System.out.print(Dieu.toString());
    	 *
    	 */
    	System.out.println(Dieu.toString());
     }
}
