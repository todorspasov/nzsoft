package com.nzsoft.olympiad;

/**
 * Zadacha #4: Fermera Toshko jivee na selo i obicha da pravi dve neshta. Parvoto e da gleda zaici i kokoshki. Nqma drugi jivotni toi.
 * Vtoroto (no ne po vajnost) e da pie rakiq.
 * Edin den otishal pri jivotnite i im prebroil krakata (edin po edin estestveno).
 * Na dva tri pati im izgubval broikata no se stegnal i nai nakraq prebroil gi dva pati podred se edin i sasht broi bili.
 * Ot poslednite prebroqvaniq vidql che krakata na kokoshkite i zaicite sa obshto X.   0 < X < 200
 * Na drugiq den julnal edna rakiq i pak otishal pri jivotnite i im prebroil glavite.
 * Vidql che glavite na kokoshkite i zaicite sa obshto Y. 0 < Y < 100
 * ======================================================
 * Vaprosa e da kajete dali Fermera Toshko e piqn ili ne.
 * Kak? Kato smetnete broq na kokoshki i zaici sprqmo broq na krakata X i na glavite Y koito Toshko kazva.
 * Kato rezultat varnete dve chisla: PARVOTO e broq na kokoshki, VTOROTO e broq na zaici
 * ======================================================
 * Ako Fermera Toshko e mnogo piqn (i ne moje da otkriete reshenie), togava toi shte gi e prebroil greshno i prosto mu varnete 0,0
 * ======================================================
 * Za mnogo liubopitnite da znaqt che nqma kuci zaici, nito kuci kokoshki Fermera Toshko.
 * Tova e zashtoto edna lisica minava vsqka vecher i tezi koito ne mogat da tichat (kucite) gi izqjda.
 * ======================================================
 * Primeren vhod (kogato ima reshenie): 26 8
 * Primeren izhod: 3 5
 * Obqsnenie kam primerniq izhod: 
 *  - tai kato 3 kokoshki i 5 zaici sa obshto 8 glavi, to tova izpalnqva uslovieto za obsht broi na glavi.
 *  - 3 kokoshki imat 3x2 = 6 kraka, 5 zaeka imat 5x4=20 kraka, znachi obshto ima 6+20=26 glavi koeto izpalnqva uslovieto ot vhoda za obsht broi kraka.
 * Sledovatelno reshenie e (3 kokoshki i 5 zaeka).
 * 
 * Primeren vhod (kogato Toshko gi e broil piqn i nqma reshenie): 17 5
 * Primeren izhod: 0 0
 * Obqsnenie kam primerniq izhod:
 *  - nikoq vazmojna kombinaciq (ot edin do 5 zaeka sas saotvetniq broi kokoshki) ne moje da dade 17 kraka.
 */
public class Problem4 {

	public int[] calculate(int broiKraka, int broiGlavi) {
		return new int[] { broiKraka / 2, broiGlavi / 2};
	}
}
