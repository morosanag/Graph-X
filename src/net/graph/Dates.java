package net.graph;


public class Dates {

	public double date[][]= new double[6][100];
	public String units[][]	= new String[6][100];
	public int[] numbers	= new int [6];
	public static String temp [][] = new String[8][8];
	public static String exception;

	public Dates(boolean ok)
	{
		if(ok==true)
		{
			units[0][0]= "meter";               		date[0][0]=1;   
			units[0][1]= "centimeter [cm]";      		date[0][1]=100;
			units[0][2]= "milimeter [mm]";      			date[0][2]=1000 ;
			units[0][3]= "foot [ft]";            		date[0][3]=3.2808 ;
			units[0][4]= "inch [in]";            		date[0][4]=39.37 ;
			units[0][5]= "kilometer [km]";       		date[0][5]=1.0e-3 ;
			units[0][6]= "yard [yd]";           			date[0][6]=1.0936 ;
			units[0][7]= "mile [mi]";           			date[0][7]=6.0e-4 ;


			units[1][0]="square meter [m²]";			date[1][0]= 1;
			units[1][1]="square kilometer [km²]";       date[1][1]= 1.0e-6;
			units[1][2]="square hectometer [hm²]";		date[1][2]= 1.0e-4;
			units[1][3]="square centimeter [cm²]";      date[1][3]= 10000;
			units[1][4]="hectare [ha]";        			date[1][4]= 1.0e-4;
			units[1][5]="are [a]";						date[1][5]= 0.01;
			units[1][6]="square yard [yd²]";			date[1][6]= 1.1959;
			units[1][7]="square foot [ft²]";        	date[1][7]= 10.7639;


			units[2][0]="Kelvin [K]";	
			units[2][1]="Celsius [°C]";	
			units[2][2]="Fahrenheit [°F]";	
			units[2][3]="Rankine [°R] ";	
			units[2][4]="Delisle [°De] ";	
			units[2][5]="Newton [°N] ";	
			units[2][6]="Réaumur [°Ré] ";	
			units[2][7]="Rømer [°Rø]";	


			units[3][0]="cubic meter [m³]";			date[3][0]= 1;
			units[3][1]="cubic decimeter [dm³]";		date[3][1]= 1000;
			units[3][2]="cubic centimeter [cm³]";		date[3][2]= 1.0e+6;
			units[3][3]="liter [L,l]";					date[3][3]= 1000;
			units[3][4]="hectoliter [hL]";				date[3][4]= 10;
			units[3][5]="barrel [oil]";					date[3][5]= 6.2898;
			units[3][6]="gallon (US)";					date[3][6]= 264.172;
			units[3][7]="cubic foot [ft³]";			date[3][7]= 35.3146;


			units[4][0]="kilogram  [kg]";				date[4][0]= 1;
			units[4][1]="gram  [g]";					date[4][1]= 1000;
			units[4][2]="centigram  [cg]";				date[4][2]= 1.0e+5;
			units[4][3]="miligram  [mg]";				date[4][3]= 1.0e+6;
			units[4][4]="pound  [lb]";					date[4][4]= 2.2046;
			units[4][5]="ounce  [oz]";					date[4][5]= 35.274;
			units[4][6]="kilopound";					date[4][6]= 2.2046e-3;
			units[4][7]="quintal  [cwt]";				date[4][7]= 0.01;


			units[5][0]="year  [y]";					date[5][0]= 1;
			units[5][1]="seconds  [s]";					date[5][1]= 3.1536e+7;
			units[5][2]="milisecond  [ms]";				date[5][2]= 3.1536e+10;
			units[5][3]="minute  [min]";				date[5][3]= 5.256e+5;
			units[5][4]="hour  [h]";					date[5][4]= 8760;
			units[5][5]="day  [d]";						date[5][5]= 365;
			units[5][6]="week";						date[5][6]= 52.1429;
			units[5][7]="month";						date[5][7]= 12;


			numbers[0]=8;
			numbers[1]=8;
			numbers[2]=8;
			numbers[3]=8;
			numbers[4]=8;
			numbers[5]=8;

		}
		else
		{
			units[0][0]= "meter [m]";               		date[0][0]=1;    
			units[0][1]= "angstrom [au]";        		date[0][1]=1.0e+10;
			units[0][2]= "centimeter [cm]";      		date[0][2]=100;
			units[0][3]= "chain";               		date[0][3]=0.0497;
			units[0][4]= "decimeter [dm]";       		date[0][4]=10;
			units[0][5]= "fathom";              		date[0][5]=0.5468 ;
			units[0][6]= "foot [ft]";            		date[0][6]=3.2808 ;
			units[0][7]= "furlong";             		date[0][7]=4.9e-3 ;
			units[0][8]= "inch [in]";            		date[0][8]=39.37 ;
			units[0][9]= "kilometer [km]";       		date[0][9]=1.0e-3 ;
			units[0][10]= "league";             		date[0][10]=2.0e-4 ;
			units[0][11]= "light year";         		date[0][11]=1.057e-16 ;
			units[0][12]= "mile [mi]";           		date[0][12]=6.0e-4 ;
			units[0][13]= "milimeter [mm]";      		date[0][13]=1000 ;
			units[0][14]= "micron";             		date[0][14]=1.0e+6 ;
			units[0][15]= "nanometer [nm]";      		date[0][15]=1.0e+9 ;
			units[0][16]= "nautical mile";      		date[0][16]=5.0e-4 ;
			units[0][17]= "parsec";             		date[0][17]=3.2407e-17 ;
			units[0][18]= "rod";                		date[0][18]=0.1988 ;
			units[0][19]= "yard [yd]";           		date[0][19]=1.0936 ;



			units[1][0]="square meter [m²]";			date[1][0]= 1;
			units[1][1]="square kilometer [km²]";       date[1][1]= 1.0e-6;
			units[1][2]="square hectometer [hm²]";		date[1][2]= 1.0e-4;
			units[1][3]="square dekameter [dam²]";     	date[1][3]= 0.01;
			units[1][4]="square decimeter [dm²]";		date[1][4]= 100;
			units[1][5]="square centimeter [cm²]";      date[1][5]= 10000;
			units[1][6]="square milimiter [mm²]";		date[1][6]= 1.0e+6;
			units[1][7]="square micrometer [um²]";      date[1][7]= 1.0e+12;
			units[1][8]="square nanometer [nm²]";		date[1][8]= 1.0e+18;
			units[1][9]="hectare [ha]";        			date[1][9]= 1.0e-4;
			units[1][10]="are [a]";						date[1][10]= 0.01;
			units[1][11]="barn [b]";        				date[1][11]= 1.0e+28;
			units[1][12]="square mile [mi²]";			date[1][12]= 3.86e-7;
			units[1][13]="square yard [yd²]";			date[1][13]= 1.1959;
			units[1][14]="square foot [ft²]";        	date[1][14]= 10.7639;
			units[1][15]="square inch [in²]";			date[1][15]= 1550.0031;
			units[1][16]="circular inch";        		date[1][16]= 1973.5252;
			units[1][17]="township";					date[1][17]= 1.1e-8 ;
			units[1][18]="section";        				date[1][18]= 3.86e-7;
			units[1][19]="acre [ac]";					date[1][19]= 2.4711e-4;
			units[1][20]="rood";						date[1][20]= 9.8842e-4;
			units[1][21]="square chain [ch²]";        	date[1][21]= 2.4711e-3;
			units[1][22]="square rod";					date[1][22]= 0.0395;
			units[1][23]="square perch";				date[1][23]= 0.0395;
			units[1][24]="square pole";        			date[1][24]= 0.0395;
			units[1][25]="square mil [mil²]";			date[1][25]= 1.55e+9;
			units[1][26]="circular mil";        		date[1][26]= 1.9735e+9;
			units[1][27]="homestead";					date[1][27]= 1.544e-6;
			units[1][28]="sabin";        				date[1][28]= 10.7639;
			units[1][29]="arpent";						date[1][29]= 2.4745e-4;
			units[1][30]="cuerda";        				date[1][30]= 2.5443e-4;
			units[1][31]="plaza";						date[1][31]= 1.5625e-4;
			units[1][32]="varas castellanas cuad";      date[1][32]= 1.4311;
			units[1][33]="varas conuqueras cuad";		date[1][33]= 0.159 ;
			units[1][34]="Electron cross section";      date[1][34]= 1.5032e+28;


			units[2][0]="Kelvin [K]";	
			units[2][1]="Celsius [°C]";	
			units[2][2]="Fahrenheit [°F]";	
			units[2][3]="Rankine [°R] ";	
			units[2][4]="Delisle [°De] ";	
			units[2][5]="Newton [°N] ";	
			units[2][6]="Réaumur [°Ré] ";	
			units[2][7]="Rømer [°Rø]";	


			units[3][0]="cubic meter [m³]";				date[3][0]= 1;
			units[3][1]="cubic kilometer [km³]";		date[3][1]= 1.0e-9;
			units[3][2]="cubic decimeter [dm³]";		date[3][2]= 1000;
			units[3][3]="cubic centimeter [cm³]";		date[3][3]= 1.0e+6;
			units[3][4]="cubic milimeter [mm³]";		date[3][4]= 1.0e+9;
			units[3][5]="liter [L,l]";					date[3][5]= 1000;
			units[3][6]="exaliter [EL]";					date[3][6]= 1.0e-15;
			units[3][7]="petaliter [PL]";				date[3][7]= 10.0e-13;
			units[3][8]="teraliter [TL]";				date[3][8]= 1.0e-9;
			units[3][9]="gigaliter [GL]";				date[3][9]= 1.0e-6;
			units[3][10]="megaliter [ML]";				date[3][10]= 1.0e-3;
			units[3][11]="kiloliter [KL]";				date[3][11]= 1;
			units[3][12]="hectoliter [hL]";				date[3][12]= 10;
			units[3][13]="dekaliter [daL]";				date[3][13]= 100;
			units[3][14]="deciliter [dL]";				date[3][14]= 10000;
			units[3][15]="centiliter [cL]";				date[3][15]= 1.0e+5;
			units[3][16]="mililiter [mL]";				date[3][16]= 1.0e+6;
			units[3][17]="microliter [uL]";				date[3][17]= 1.0e+9;
			units[3][18]="nanoliter [nL]";				date[3][18]= 1.0e+12;
			units[3][19]="picoliter [pL]";				date[3][19]= 1.0e+15;
			units[3][20]="femtoliter [fL]";				date[3][20]= 1.0e+18;
			units[3][21]="attoliter [aL]";				date[3][21]= 1.0e+21;
			units[3][22]="cc [cc,cm³]";					date[3][22]= 1.0e+6;
			units[3][23]="drop";						date[3][23]= 2.0e+7;
			units[3][24]="barrel [oil]";					date[3][24]= 6.2898;
			units[3][25]="barrel (US)";					date[3][25]= 8.3864;
			units[3][26]="barrel (UK)";					date[3][26]= 6.1102;
			units[3][27]="gallon (US)";					date[3][27]= 264.172;
			units[3][28]="gallon (UK)";					date[3][28]= 219.9692;
			units[3][29]="quart (UK)";					date[3][29]= 1056.6882;
			units[3][30]="quart (US)";					date[3][30]= 879.8769;
			units[3][31]="pint (US)";					date[3][31]= 2113.3764;
			units[3][32]="pint (UK)";					date[3][32]= 1759.7539;
			units[3][33]="cup (US)";						date[3][33]= 4226.7528;
			units[3][34]="cup (metric)";					date[3][34]= 4000;
			units[3][35]="cup (UK)";						date[3][35]= 3519.5079;
			units[3][36]="fluid ounce (US)";				date[3][36]= 3.3814e+4;
			units[3][37]="fluid ounce (UK)";				date[3][37]= 3.5195e+4;
			units[3][38]="tablespoon (US)";				date[3][38]= 6.7628e+4;
			units[3][39]="tablespoon (metric)";			date[3][39]= 6.6667e+4;
			units[3][40]="tablespoon (UK)";				date[3][40]= 5.6312e+4;
			units[3][41]="dessertspoon (US)";			date[3][41]= 1.0144e+5;
			units[3][42]="dessertspoon (UK)";			date[3][42]= 8.4468e+4;
			units[3][43]="teaspoon (US)";				date[3][43]= 2.0288e+5;
			units[3][44]="teaspoon [metric]";			date[3][44]= 2.0e+5;
			units[3][45]="teaspoon (UK)";				date[3][45]= 1.6894e+5;
			units[3][46]="gill (US)";					date[3][46]= 8453.5056;
			units[3][47]="gill (UK)";					date[3][47]= 7039.0159;
			units[3][48]="minim (US)";					date[3][48]= 1.6231e+7;
			units[3][49]="minim (UK)";					date[3][49]= 1.6894e+7;
			units[3][50]="cubic mile [mi³]";			date[3][50]= 2.3991e-10;
			units[3][51]="cubic yard [yd³]";			date[3][51]= 1.3079;
			units[3][52]="cubic foot [ft³]";			date[3][52]= 35.3146;
			units[3][53]="cubic inch [in³]";			date[3][53]= 6.1024e+4;
			units[3][54]="ton register  [ton reg]";		date[3][54]= 0.3531;
			units[3][55]="ccf";							date[3][55]= 0.3531;
			units[3][56]="hundred-cubic foot";			date[3][56]= 0.3531;
			units[3][57]="acre-foot [ac*ft]";			date[3][57]= 8.0e-4;
			units[3][58]="acre-foot (US)";				date[3][58]= 8.0e-4;
			units[3][59]="acre-inch [ac*in]";			date[3][59]= 9.7e-3;
			units[3][60]="dekastere";					date[3][60]= 0.1;
			units[3][61]="stere [st]";					date[3][61]= 1;
			units[3][62]="decistere";					date[3][62]= 10;
			units[3][63]="cord [cd]";					date[3][63]= 0.2758;
			units[3][64]="tun";							date[3][64]= 1.0483;
			units[3][65]="hongshead";					date[3][65]= 4.1932;
			units[3][66]="board foot";					date[3][66]= 423.776;
			units[3][67]="dram  [dr)";					date[3][67]= 2.7051e+5;
			units[3][68]="cor (Biblical)";				date[3][68]= 4.5454;
			units[3][69]="homer (Biblical)";			date[3][69]= 4.5454;
			units[3][70]="bath (Biblical)";				date[3][70]= 45.4545;
			units[3][71]="hin (Biblical)";				date[3][71]= 272.7272;
			units[3][72]="cab (Biblical)";				date[3][72]= 818.1818;
			units[3][73]="log (Biblical)";				date[3][73]= 3272.7272;
			units[3][74]="Taza (Spanish)";				date[3][74]= 4226.7528;
			units[3][75]="Earth's volume";				date[3][75]= 9.2336e-22;


			units[4][0]="kilogram  [kg]";				date[4][0]= 1;
			units[4][1]="gram  [g]";					date[4][1]= 1000;
			units[4][2]="exagram  [Eg]";				date[4][2]= 1.0e-15;
			units[4][3]="petagram  [Pg]";				date[4][3]= 10.0e-13;
			units[4][4]="teragram  [Tg]";				date[4][4]= 1.0e-9;
			units[4][5]="gigagram  [Gg]";				date[4][5]= 1.0e-6;
			units[4][6]="megagram  [Mg]";				date[4][6]= 1.0e-3;
			units[4][7]="hectogram  [hg]";				date[4][7]= 10;
			units[4][8]="dekagram  [dag]";				date[4][8]= 100;
			units[4][9]="decigram  [dg]";				date[4][9]= 10000;
			units[4][10]="centigram  [cg]";				date[4][10]= 1.0e+5;
			units[4][11]="miligram  [mg]";				date[4][11]= 1.0e+6;
			units[4][12]="microgram  [ug]";				date[4][12]= 1.0e+9;
			units[4][13]="nanogram  [ng]";				date[4][13]= 1.0e+12;
			units[4][14]="picogram  [pg]";				date[4][14]= 1.0e+15;
			units[4][15]="femtogram [fg]";				date[4][15]= 1.0e+18;
			units[4][16]="attogram  [ag]";				date[4][16]= 1.0e+21;
			units[4][17]="kilogram-force square second/meter";					date[4][17]= 0.102;
			units[4][18]="kilopound";					date[4][18]= 2.2046e-3;
			units[4][19]="pound-force square second/foot";					date[4][19]= 0.0685;
			units[4][20]="pound  [lb]";					date[4][20]= 2.2046;
			units[4][21]="ounce  [oz]";					date[4][21]= 35.274;
			units[4][22]="poundal [pdl]";				date[4][22]= 70.9888;
			units[4][23]="ton (metric)";				date[4][23]= 1.0e-3;
			units[4][24]="kiloton  [kt]";				date[4][24]= 1.0e-6;
			units[4][25]="quintal  [cwt]";				date[4][25]= 0.01;
			units[4][26]="hundredweight (US)";			date[4][26]= 0.022;
			units[4][27]="quarter (US)";				date[4][27]= 0.0882;
			units[4][28]="tonne  [t]";					date[4][28]= 1.0e-3;
			units[4][29]="scruple (apothecary)";		date[4][29]= 771.6179;
			units[4][30]="carat";						date[4][30]= 5000;
			units[4][31]="grain";						date[4][31]= 1.5432e+4;
			units[4][32]="Planck mass";					date[4][32]= 4.5941e+7;
			units[4][33]="Atomic mass unit  [u]";		date[4][33]= 6.0221e+26;
			units[4][34]="Electron mass (rest)";		date[4][34]= 1.0978e+30;
			units[4][35]="Proton mass";					date[4][35]= 5.9786e+26;
			units[4][36]="Neutron mass";				date[4][36]= 5.9704e+26;
			units[4][37]="Earth's mass";				date[4][37]= 1.6734e-25;
			units[4][38]="Sun's mass";					date[4][38]= 5.0e-31;


			units[5][0]="year  [y]";						date[5][0]= 1;	
			units[5][1]="seconds  [s]";					date[5][1]= 31536000;       		
			units[5][2]="milisecond  [ms]";				date[5][2]= 3.1536e+10;			
			units[5][3]="microsecond  (US)";				date[5][3]= 3.1536e+13;			
			units[5][4]="nanosecond  [ns]";				date[5][4]= 3.1536e+16; 		
			units[5][5]="picosecond  [ps]";				date[5][5]= 3.1536e+19;		
			units[5][6]="femtosecond  [fs]";				date[5][6]= 3.1536e+22;		
			units[5][7]="attosecond  [as]";				date[5][7]= 3.1536e+25;		
			units[5][8]="shake";						date[5][8]= 3.1536e+15;			
			units[5][9]="minute  [min]";					date[5][9]= 525600;				
			units[5][10]="hour  [h]";					date[5][10]= 8760;		
			units[5][11]="day  [d]";						date[5][11]= 365;		
			units[5][12]="week";						date[5][12]= 52.142857143;		
			units[5][13]="month";						date[5][13]= 12;		
			units[5][14]="month (synodic)";				date[5][14]= 12.360060412;		
			units[5][15]="day (sidereal)";				date[5][15]= 365.999339168;		
			units[5][16]="hour (sidereal)";				date[5][16]= 8783.984140029;		
			units[5][17]="minute (sidereal)";			date[5][17]= 527039.04840172;		
			units[5][18]="second (sidereal(UK)";			date[5][18]= 31622342.904103;		
			units[5][19]="fortnight";					date[5][19]= 26.071428571;		
			units[5][20]="decade";						date[5][20]= 0.1;		
			units[5][21]="century";						date[5][21]= 0.01;		
			units[5][22]="millennium";					date[5][22]= 0.001;		
			units[5][23]="septennial";					date[5][23]= 0.142857143;		
			units[5][24]="Planck Time";					date[5][24]= 5.850227064e+50;	

			numbers[0]=20;   
			numbers[1]=35;  
			numbers[2]=8;  
			numbers[3]=76;
			numbers[4]=39;
			numbers[5]=25;
		}
		// from kelvin
		temp[0][0]="x";
		temp[0][1]="x - 273.15";
		temp[0][2]="x * 9 / 5 - 459.67";
		temp[0][3]="x * 9 / 5  ";
		temp[0][4]="( 373.15 - x ) * 3 / 2";
		temp[0][5]="( x - 273.15 ) * 33 / 100";
		temp[0][6]="( x - 273.15 ) * 4 / 5";
		temp[0][7]="( x - 273.15 ) * 21 / 40 + 7.5";

		// to kelvin
		temp[1][0]="x + 273.15";
		temp[2][0]="( x + 459.67) * 5 / 9" ;
		temp[3][0]="x * 5 / 9" ;
		temp[4][0]="373.15 - x * 2 / 3";
		temp[5][0]="x * 100 / 33 + 273.15";
		temp[6][0]="x * 5 / 4 + 273.15";
		temp[7][0]="273.15 + ( x - 7.5) * 40 / 21";

	}

	public static String [] evaluateTemp (int x, double nr)
	{
		String res[] = new String [8];
		try
		{
			for(int i=0;i<8;i++)
			{
				String xx = Evaluator.evaluate(temp[x][0].replace("x", Evaluator.change(nr+"")));
				res[i]=converter(Double.parseDouble(Evaluator.evaluate(temp[0][i].replace("x",Evaluator.change(xx)))));

			}
		}
		catch(Exception ex)
		{

		}
		return res;
	}

	public String [] getResults(double x, int module, int nr)
	{
		String [] results = new String[numbers[module]];
		if(module==2)
		{
			return evaluateTemp (nr,x);
		}
		else
		{
			for(int i=0;i<numbers[module];i++)
			{
				try
				{
					results[i]=converter(x*date[module][i]/date[module][nr]);
				}
				catch(Exception ex)
				{
					results[i]="";
				}
			}
		}
		return results;
	}



	public static String converter(double number)
	{
		double sign = Math.signum(number);
		number=Math.abs(number);
		double res;
		if(number==0)
		{
			res = ((int)number);
		}
		else if(number<=10000 && number>=0.01)
		{
			if((int) number == number)
			{
				res = ((int)number);
			}
			else
			{
				res = (round(number));
			}    
		}
		else if(number<1)
		{
			res = Double.parseDouble(minus(number));
		}
		else
		{
			res =  Double.parseDouble(plus(number));
		}
		return res*sign+"";
	}

	public static String plus(double x)
	{
		int k=0;
		while(!isDigit((int)x))
		{
			System.out.println(1);
			x/=10;
			k++;
		}
		if(k==0)
		{
			return (round(x))+"";
		}
		else
		{
			return (round(x))+"e+"+k;
		}
	}
	public static String minus(double x)
	{
		int k=0;
		while(!isDigit((int)x))
		{
			System.out.println(2 +" "+x );
			x*=10;
			k++;
		}
		if(k==0)
		{
			return (round(x))+"";
		}
		else
		{
			return (round(x))+"e-"+k;
		}
	}

	public static double round(double x)
	{
		return (Math.round(x* 10000.0) / 10000.0);
	}
	public static boolean isDigit(int x)
	{
		return x!=0 && x/10==0;
	}

}
