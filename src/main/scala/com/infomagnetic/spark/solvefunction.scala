package com.infomagnetic.spark

import com.infomagnetic.CEA._
import com.infomagnetic.example.inverse1D.shaw.ShawFitness
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

import scala.util.Random

object Functionfitness {
  def calculatefitness(individual: Individual) = {
    val properties = individual.individualProperites
    val a1 = properties(0)
    val a2 = properties(1)
    val a3 = properties(2)

    val d1 = properties(3)
    val d2 = properties(4)
    val d3 = properties(5)

    val x1 = properties(6)
    val x2 = properties(7)
    val x3 = properties(8)

    val f1 = -3.673722772837135125889027463262 - 1.901049563237100947787894256180*a1 + 2.446664136051510451963508785411*a2 + 0.352449190881617191450914106098*a3 - 0.080678880471016310935593535545*d1*d1 - 2.355341298007295100927643829947*d1*d2 + 3.164976243199104352812241389501*d2*d2 - 3.164300389464483131296739775039*d1*d3 + 4.547651571237727627575146247356*d2*d3 + 0.589425410109047084012379609306*d3*d3 + 2.053111087903660217619929276176*x1 - 0.049008651463354954807754575332*a1*x1 + 0.449328488463151680083912023179*a2*x1 - 0.598822426368046775164636726960*a3*x1 - 1.860662957082719649242913286158*d1*d1*x1 + 2.168202286277043769658087783623*d1*d2*x1 + 0.812237176510381865916563692175*d2*d2*x1 + 0.254638658487733504048931670793*d1*d3*x1 - 0.012352533917232111736173543193*d2*d3*x1 - 1.004685307331322434293579682193*d3*d3*x1 + 0.047913874415125051683987928159*d1*d1*x1*x1 + 0.434664251787881787925459541154*d1*d2*x1*x1 + 0.042421380580982545322659430727*d2*d2*x1*x1 - 0.584996149651182728072115550801*d1*d3*x1*x1 - 0.060656507579928069970346666894*d2*d3*x1*x1 + 0.005492493834142506361328497432*d3*d3*x1*x1 - 2.943982276626714364206781478908*x2 - 0.327625224183525172637322793795*a1*x2 - 0.079566705183259212069721421528*a2*x2 + 0.275786841993091385720373645797*a3*x2 + 0.139010176239506087766293619222*d1*d1*x2 - 2.474321103371609357462461264844*d1*d2*x2 + 2.278461657125127325629244531468*d2*d2*x2 - 0.916734470143141296457891427302*d1*d3*x2 + 1.019991491480039234599420093212*d2*d3*x2 + 0.526510443262080950811243328217*d3*d3*x2 + 0.316364488164676797641499246181*d1*d1*x1*x2 - 0.192564267660046619982397162323*d1*d2*x1*x2 + 0.429828661561347655255059489452*d2*d2*x1*x2 + 0.311719492076277879962751455379*d1*d3*x1*x2 - 0.472838932223294717981571397782*d2*d3*x1*x2 - 0.11346417339667085761356024327*d3*d3*x1*x2 + 0.026353117850533587892724322142*d1*d1*x2*x2 - 0.312484238821011532494540867488*d1*d2*x2*x2 - 0.077443900733408142220732290147*d2*d2*x2*x2 - 0.082877487318623762369334155196*d1*d3*x2*x2 + 0.253688872552973612879404456622*d2*d3*x2*x2 + 0.051090782882874554328007968005*d3*d3*x2*x2 + 0.466350163382818087885573220231*x3 + 0.691393689580923538771943170635*a1*x3 - 0.209374736817273781480303470936*a2*x3 - 0.058416594528404819432269347358*a3*x3 - 0.029941109380318931408416436785*d1*d1*x3 + 1.279783374013104366144033146484*d1*d2*x3 - 0.680102998955726361972649663787*d2*d2*x3 - 0.676610176828385780821687609729*d1*d3*x3 + 1.885205333922415809623533774246*d2*d3*x3 + 0.243693944953227205495492880341*d3*d3*x3 + 0.674420720326424626362724378058*d1*d1*x1*x3 - 0.117212874473462715109006166184*d1*d2*x1*x3 - 0.094927250128733066154100949568*d2*d2*x1*x3 - 0.129597788011211990621415329390*d1*d3*x1*x3 + 0.531798448355836723058114159546*d2*d3*x1*x3 - 0.579493470197691560208623428490*d3*d3*x1*x3 + 0.065830305707251753045242157957*d1*d1*x2*x3 + 0.742110304483918972133229264472*d1*d2*x2*x3 - 0.190479433786600628255198248704*d2*d2*x2*x3 - 0.175960215093713726761765520089*d1*d3*x2*x3 - 0.218846725162705696908485549048*d2*d3*x2*x3 + 0.256309739493852381300440406661*d3*d3*x2*x3 + 0.021560756564591463791263606017*d1*d1*x3*x3 - 0.122180012966870255430918673666*d1*d2*x3*x3 + 0.035022520152425596898072859420*d2*d2*x3*x3 + 0.667873636969806490441449705998*d1*d3*x3*x3 - 0.193032364973045542909057789728*d2*d3*x3*x3 - 0.056583276717017060689336465437*d3*d3*x3*x3
    val f2 = -4.226241920437621054252173592476 - 2.972794787892629742032113426830*a1 + 5.016632924244631919208359249751*a2 + 1.314896768969127549553377320989*a3 - 0.922682464719457541024171411445*d1*d1 - 0.378535666770835147148659842195*d1*d2 + 3.266310439656844733687245413301*d2*d2 - 3.848211016826229871669710303465*d1*d3 + 8.038728474742422679241686174747*d2*d3 + 1.88261394550023386158909959062*d3*d3 + 2.757501467159591251778217585830*x1 - 0.012746516917068393929727589776*a1*x1 + 0.044897871994713923146377385607*a2*x1 - 0.612199227117876883063051865482*a3*x1 - 2.883393410601271076337238829201*d1*d1*x1 + 4.283490978685708590894869774090*d1*d2*x1 + 1.010076236652001632652267158751*d2*d2*x1 + 1.082667575062461466319540421168*d1*d3*x1 - 0.106468059352963997186504310344*d2*d3*x1 - 0.884184293210321808093245915379*d3*d3*x1 + 0.012346225576537749600034890038*d1*d1*x1*x1 + 0.040995723225694244529351713704*d1*d2*x1*x1 + 0.008778354384135310474610915463*d2*d2*x1*x1 - 0.592899449719339933785752523839*d1*d3*x1*x1 - 0.119957798384768434415427766406*d2*d3*x1*x1 + 0.003567871192402439125423974575*d3*d3*x1*x1 - 5.995773051771500544281457843025*x2 + 0.081118758198896596627144163034*a1*x2 - 0.035951027722350621714334167197*a2*x2 + 0.261791087492089625897241996366*a3*x2 + 0.568077302927682938755579916780*d1*d1*x2 - 3.735560454027882542153170100725*d1*d2*x2 + 4.804682842930494907529711937063*d2*d2*x2 - 0.603859700385065004810720048879*d1*d3*x2 + 2.331363627359668980524373645666*d2*d3*x2 + 0.623012905913322697996165989181*d3*d3*x2 + 0.080899110767185313605026831851*d1*d1*x1*x2 - 0.039428655404496270554957450966*d1*d2*x1*x2 + 0.036181371036588249955190970196*d2*d2*x1*x2 + 0.362492620562670376844076570378*d1*d3*x1*x2 - 0.529321557075266890639769019481*d2*d3*x1*x2 - 0.11708048180377356356021780204*d3*d3*x1*x2 + 0.014814132718311281285567710461*d1*d1*x2*x2 + 0.084635509706648529808938915091*d1*d2*x2*x2 - 0.034599863120009953065605639773*d2*d2*x2*x2 - 0.032497561656840947443507433287*d1*d3*x2*x2 + 0.245166184398428471801552201761*d2*d3*x2*x2 + 0.049413995838321234351173350234*d3*d3*x2*x2 - 0.253477632403881112637531017340*x3 + 0.648640704601684850595001023869*a1*x3 - 0.139835090654113128618007371433*a2*x3 - 0.054750306450443805697225542268*a3*x3 + 0.076843099489260041640458962930*d1*d2*x3 + 1.137933171992445983301149705711*d1*d2*x3 - 1.017401773510503566568591175986*d2*d2*x3 - 1.910011594134784019660018548928*d1*d3*x3 + 4.375932836459104931286211927993*d2*d3*x3 + 1.194036306425124637565663230395*d3*d3*x3 + 0.627737074511703277108935163395*d1*d1*x1*x3 - 0.004388873938455206548994710165*d1*d2*x1*x3 - 0.035631015455567955861268116296*d2*d2*x1*x3 - 0.094780437044345432673430029652*d1*d3*x1*x3 + 0.146604721538687900379920969881*d2*d3*x1*x3 - 0.592106059056135321247667047098*d3*d3*x1*x3 + 0.115059901373569651347022309002*d1*d1*x2*x3 + 0.633315101357205681303504710226*d1*d2*x2*x3 - 0.127940997935843860088141409770*d2*d2*x2*x3 + 0.221892408995669020979034374247*d1*d3*x2*x3 - 0.162218295397956609271658184752*d2*d3*x2*x3 + 0.243000899309413511435163718773*d3*d3*x2*x3 + 0.027160358294849030885602600499*d1*d1*x3*x3 - 0.125631232932342774338290628796*d1*d2*x3*x3 + 0.025821508735874642590994724310*d2*d2*x3*x3 + 0.625397011376180881229259957127*d1*d3*x3*x3 - 0.125208386013660037386124435354*d2*d3*x3*x3 - 0.052981867030723673476597324810*d3*d3*x3*x3
    val f3 = -2.653344805750162406854295943950 - 2.098718373496504696618103480432*a1 + 1.061794618680395707599473098525*a2 + 0.696564216964770999140910134883*a3 - 0.192780562852121220211572825942*d1*d1 - 3.343957876131940002135507363886*d1*d2 + 1.741136907967946581396598865677*d2*d2 - 3.265299425772196195401989156178*d1*d3 + 2.826598465087683606096145068317*d2*d3 + 1.104988460634337045669269904215*d3*d3 + 1.600548813291070683390079824556*x1 - 0.010354676756403119430888348221*a1*x1 + 0.148501367467044245993054284713*a2*x1 + 0.084275577812434518028596837935*a3*x1 - 2.032506460118767675744039403111*d1*d1*x1 + 0.668497113132630104372532102112*d1*d2*x1 + 0.423612235833024947700595720464*d2*d2*x1 + 1.043245366746246684209532814892*d1*d3*x1 + 0.300852418318078277657598188541*d2*d3*x1 + 0.008345410994672044653363858089*d3*d3*x1 + 0.010023307029420846812676097469*d1*d1*x1*x1 + 0.141992702525441337308467359196*d1*d2*x1*x1 + 0.025188415685387948972018512654*d2*d2*x1*x1 + 0.083441885455101675263318430158*d1*d3*x1*x1 - 0.012427720620085680437501564282*d2*d3*x1*x1 - 0.015165108655967102159342415185*d3*d3*x1*x1 - 1.236703586973770227528237182973*x2 - 0.200722336990640863359014139621*a1*x2 + 0.018218554773295148184439936116*a2*x2 - 0.137875770978225320465964355598*a3*x2 + 0.216959885089555512942054811318*d1*d1*x2 - 2.437985256921791717116501787412*d1*d2*x2 + 1.037384333528580069829851491599*d2*d2*x2 - 1.015044821730094573530084409842*d1*d3*x2 + 0.770271654538476895631744777955*d2*d3*x2 - 0.017640631644365355243669119943*d3*d3*x2 + 0.193137426697820870494168203906*d1*d1*x1*x2 - 0.042892044667025715350460075675*d1*d2*x1*x2 + 0.143999289429645038156975357037*d2*d2*x1*x2 - 0.109785811963370709104193742969*d1*d3*x1*x2 + 0.096170346011063286776473869388*d2*d3*x1*x2 + 0.04913813726817583233719284686*d3*d3*x1*x2 + 0.022513519343009560555366027199*d1*d1*x2*x2 - 0.192503671144939324819675388263*d1*d2*x2*x2 + 0.017287115273301478177946444374*d2*d2*x2*x2 - 0.042478077835471481828040614154*d1*d3*x2*x2 - 0.125567597016540634375960487778*d2*d3*x2*x2 - 0.039800634616311038733312471573*d3*d3*x2*x2 - 0.274682121281968245968874085600*x3 - 0.057206748676415617796868040792*a1*x3 + 0.163119320610087638767765462078*a2*x3 + 0.058512526325801379651777834162*a3*x3 - 0.463477628323512829358146936836*d1*d1*x3 + 0.699498827152522612789697172207*d1*d2*x3 - 0.009002503113177995748081792238*d2*d2*x3 - 1.904793505360074684042170467888*d1*d3*x3 + 1.170762998953766366666827194038*d2*d3*x3 + 0.747162252718659071075102814675*d3*d3*x3 + 0.057636805991016470843622933742*d1*d1*x1*x3 + 0.183315223054171573694146824049*d1*d2*x1*x3 - 0.011001207411894065084244603965*d2*d2*x1*x3 + 0.075607429687148781807072467955*d1*d3*x1*x3 + 0.098126827341766002412504307131*d2*d3*x1*x3 + 0.068638013402910535927867537707*d3*d3*x1*x3 + 0.037408178178387272576373714484*d1*d1*x2*x3 - 0.016332948153481233758707334908*d1*d2*x2*x3 + 0.150035674099067251033575561051*d2*d2*x2*x3 - 0.241735242744636101281645148401*d1*d3*x2*x3 + 0.155625166879147620713335427784*d2*d3*x2*x3 - 0.112627495920679978457201846566*d3*d3*x2*x3 + 0.012490212313588713742689929730*d1*d1*x3*x3 + 0.050510968619497987511208029067*d1*d2*x3*x3 - 0.042475530958689427149964957029*d2*d2*x3*x3 - 0.040963807619630193435277816004*d1*d3*x3*x3 + 0.137995317636626314813462052061*d2*d3*x3*x3 + 0.054965743272278140892654886759*d3*d3*x3*x3
    val f4 = -5.763244514445151627040305692626 - 1.320545584179262044166591749672*a1 + 1.737449013321460622449876609607*a2 + 3.190777966826714828751931918304*a3 + 0.135602692125008272990337619464*d1*d1 - 2.120793319173346780823047104504*d1*d2 + 2.555600095671723823645625161336*d2*d2 - 1.599056024514145148841119111536*d1*d3 + 6.366084739227993246781071280872*d2*d3 + 3.072041726648419530404342911824*d3*d3 + 1.543356000122693230600931044430*x1 - 0.011815157688645188565129900404*a1*x1 - 0.040966422033958471096461257027*a2*x1 - 0.457922686903414106539521482836*a3*x1 - 1.277554462188101943550255239530*d1*d1*x1 + 1.382771825671734091367201782366*d1*d2*x1 + 0.316742050237581448982263303444*d2*d2*x1 + 3.184107448384805375162241461050*d1*d3*x1 - 0.097785660699345748953297441711*d2*d3*x1 - 0.582543588172172736032939108344*d3*d3*x1 + 0.011441363694066544029014634240*d1*d1*x1*x1 - 0.042234079059411785456724262826*d1*d2*x1*x1 - 0.008889072304973774517404515276*d2*d2*x1*x1 - 0.442910911503869455527632159539*d1*d3*x1*x1 - 0.097543264831817317524569241498*d2*d3*x1*x1 + 0.020330435999040318546419149517*d3*d3*x1*x1 - 2.917836989859757389095582726495*x2 + 0.131773367125612949178272645780*a1*x2 + 0.290585727001266312439471357732*a2*x2 - 0.577557834241903429050722949464*a3*x2 + 0.244353234433204091196839654113*d1*d1*x2 - 1.338428361912102030279496307013*d1*d2*x2 + 1.957734660300142530657024512130*d2*d2*x2 - 0.963295536579915749100064037957*d1*d3*x2 + 2.933159098134556198459595454793*d2*d3*x2 + 0.715749095126410767241718560250*d3*d3*x2 + 0.129911817010832443600285995616*d1*d1*x1*x2 + 0.307578881153994655307660801542*d1*d2*x1*x2 + 0.026970053163332550926884873550*d2*d2*x1*x2 - 0.480419423941421102418817865538*d1*d3*x1*x2 - 0.557881221887595553302756599437*d2*d3*x1*x2 - 0.15688187017416499452717086916*d3*d3*x1*x2 + 0.025733550056400557563188833472*d1*d1*x2*x2 + 0.059316205575590470503635700285*d1*d2*x2*x2 + 0.255942775119250974443453424511*d2*d2*x2*x2 + 0.165312854504625210026756398348*d1*d3*x2*x2 - 0.392877947505411810780392450569*d2*d3*x2*x2 - 0.230209225062850416880264591039*d3*d3*x2*x2 - 3.165412539643545136562044548229*x3 + 0.274581527811360840826464565762*a1*x3 + 0.647570885990208016555516823592*a2*x3 + 0.234392992922673616100025233092*a3*x3 - 0.206981317207988094870248047892*d1*d1*x3 + 1.076410879458711961491366700294*d1*d2*x3 + 0.289655612435963679096381447644*d2*d2*x3 - 0.510148960489411127319807084025*d1*d3*x3 + 1.306667965385089163271912272395*d2*d3*x3 + 3.082738244415569552335911148478*d3*d3*x3 + 0.264295016993896772720483293145*d1*d1*x1*x3 + 0.685624935813181381123263875733*d1*d2*x1*x3 + 0.156141777614084470408593919481*d2*d2*x1*x3 + 0.142210444482824066749951565282*d1*d3*x1*x3 + 0.160128764592098030557349697462*d2*d3*x1*x3 - 0.420436794607981243129077212627*d3*d3*x1*x3 + 0.035781575346316574413572751836*d1*d1*x2*x3 + 0.104453796843960359261143947901*d1*d2*x2*x3 + 0.459508114576234701630502956375*d2*d2*x2*x3 + 0.103469497603974478481465195394*d1*d3*x2*x3 + 0.945102682990090267906833775125*d2*d3*x2*x3 - 0.423726539229918127216930204539*d3*d3*x2*x3 + 0.037174913750467101592203467713*d1*d1*x3*x3 - 0.017082126516178685046911437458*d1*d2*x3*x3 - 0.247053702814277199926048909235*d2*d2*x3*x3 + 0.277598056999244245500875761190*d1*d3*x3*x3 + 0.490421212337229128304961692067*d2*d3*x3*x3 + 0.209878789063810098333845441521*d3*d3*x3*x3
    val f5 = -5.509919832882665956644988141345 + 0.740490242782718466330038609157*a1 + 2.641256369405427850693968139239*a2 + 2.224814207969556312249892471299*a3 - 0.457582854929442789969323030669*d1*d1 - 0.710287204603724609952468151815*d1*d2 + 3.288206496946170994806824246226*d2*d2 - 0.483058084121843555961716643726*d1*d3 + 5.950569451159568209492167478484*d2*d3 + 2.679296190865937751807486925789*d3*d3 - 1.001194110476260797847055409454*x1 + 0.295088791586386481592174667136*a1*x1 - 0.177042859749786548011888928951*a2*x1 + 0.670001357586255257373540335417*a3*x1 + 0.477899755548928485988480122199*d1*d1*x1 + 3.017678854245843756680860572869*d1*d2*x1 + 0.442556685234046188279062580432*d2*d2*x1 + 1.683391878576028535055719584825*d1*d3*x1 + 0.317290568640295030226413794257*d2*d3*x1 + 0.080737669693286123579512706822*d3*d3*x1 + 0.263112142283449489664039258180*d1*d1*x1*x1 - 0.083789692355738242231150711199*d1*d2*x1*x1 - 0.044438369607973811211185406426*d2*d2*x1*x1 + 0.501087610576533598754964891542*d1*d3*x1*x1 + 0.225955568959936908507646207919*d2*d3*x1*x1 - 0.218673772675475678452853851753*d3*d3*x1*x1 - 3.347859442481532362659150836505*x2 + 0.646861831755990275352098403959*a1*x2 + 0.278163217844654374942472324798*a2*x2 + 1.143579228032412854584304489424*a3*x2 - 0.639687758506461854536474550485*d1*d1*x2 + 0.432003283613899878363220033981*d1*d2*x2 + 2.680871309872136836536589689258*d2*d2*x2 - 0.672106906395327226548972921467*d1*d3*x2 + 3.641449748313996765687268263493*d2*d3*x2 + 1.306675891115857380659035697732*d3*d3*x2 + 0.481140025009802965992969294413*d1*d1*x1*x2 + 0.728582908232616804603167154559*d1*d2*x1*x2 - 0.086666123592319993639303735065*d2*d2*x1*x2 + 0.582064178942380202458742854657*d1*d3*x1*x2 + 0.794073195261051599739280208299*d2*d3*x1*x2 - 0.39447390141748297235366555934*d3*d3*x1*x2 + 0.209621102904981184429832117201*d1*d1*x2*x2 + 0.481612219124246632303779662034*d1*d2*x2*x2 + 0.245865119779045411820817088048*d2*d2*x2*x2 - 0.391087927788507814024246777773*d1*d3*x2*x2 + 1.001979950302734009353508329639*d2*d3*x2*x2 - 0.036244016874064227390984970846*d3*d3*x2*x2 - 1.207529514872375296441102867857*x3 - 0.208766306782228852286330688016*a1*x3 - 1.438546583039379018635576656596*a2*x3 + 0.289109295483957374152958224430*a3*x3 + 0.318737998828238966331046579130*d1*d1*x3 + 1.409481014618434424164367310698*d1*d2*x3 - 1.421073221566481268055766181050*d2*d2*x3 + 0.792901367805632242921957247503*d1*d3*x3 + 1.267921884183108767104864333766*d2*d3*x3 + 2.309864737610617598165822469777*d3*d3*x3 + 0.110534830537466058990437570635*d1*d1*x1*x3 - 1.339106787932631641474442147384*d1*d2*x1*x3 - 0.385869884170570808857390843184*d2*d2*x1*x3 + 0.757778225755998534404552513324*d1*d3*x1*x3 + 0.479786918371916341964418621109*d2*d3*x1*x3 + 0.496404714708036867847828413819*d3*d3*x1*x3 + 0.233394344777898639502885102108*d1*d1*x2*x3 + 0.443493760942450805702745362232*d1*d2*x2*x3 - 1.232565312196272443647825402637*d2*d2*x2*x3 + 0.776301922316940081176618774102*d1*d3*x2*x3 + 0.706524159682785405590666608107*d2*d3*x2*x3 + 0.999170967418373804144940300529*d3*d3*x2*x3 + 0.053491039378468305234207140978*d1*d1*x3*x3 - 0.397822526768508390072628950835*d1*d2*x3*x3 - 0.201426750171071600609631681621*d2*d2*x3*x3 - 0.109999682788025784730718113768*d1*d3*x3*x3 - 1.227935519262670917861154537558*d2*d3*x3*x3 + 0.254917789549539905843838822600*d3*d3*x3*x3
    val f6 = -5.360980953566958383159636632588 - 1.396175839680480756932468011655*a1 + 3.097657919643613810833926436316*a2 + 1.190091219129234989537735600606*a3 + 0.116970642435067011958082379657*d1*d1 - 1.838370994014067849031061752406*d1*d2 + 3.502955210669128349522825552201*d2*d2 - 2.142253543177087340432264883994*d1*d3 + 5.877550329388782100829993159071*d2*d3 + 1.741055100462763021678728700729*d3*d3 + 0.831434833050523621299426370549*x1 - 0.042847933112365123903383335260*a1*x1 + 0.284112260961698276189968703297*a2*x1 - 0.021272383117701852720750290745*a3*x1 - 1.359247311345463186578622361830*d1*d1*x1 + 2.761951678186362059134518044934*d1*d2*x1 + 0.741683123822562725426359039608*d2*d2*x1 + 1.315167177712775867272959079429*d1*d3*x1 + 0.077426554331926097887989906181*d2*d3*x1 - 0.213870645527623160147163048327*d3*d3*x1 + 0.041824783418084391301648516979*d1*d1*x1*x1 + 0.271512957699968534803751700353*d1*d2*x1*x1 + 0.038558203314320332818136570993*d2*d2*x1*x1 - 0.014184713461818513002968841913*d1*d3*x1*x1 - 0.046515159236405139756383463657*d2*d3*x1*x1 + 0.003266580103764058483511945986*d3*d3*x1*x1 - 3.665934679296621855148072095656*x2 - 0.236307527939157179919367622590*a1*x2 - 0.072142307856565393454612677528*a2*x2 + 0.233177554885206133377253353246*a3*x2 + 0.163975458801530097993913622675*d1*d1*x2 - 1.937400370094384280620733771603*d1*d2*x2 + 2.927682816574043201530850089515*d2*d2*x2 - 0.761660487796960842858110039709*d1*d3*x2 + 1.921129053322090889553071123038*d2*d3*x2 + 0.574276403921048555623308383466*d3*d3*x2 + 0.226240085899895847733717908442*d1*d1*x1*x2 - 0.173454482012382922397279140702*d1*d2*x1*x2 + 0.266214161206676341353870871084*d2*d2*x1*x2 + 0.257699412652858968890477674144*d1*d3*x1*x2 + 0.077717963717521208065854785210*d2*d3*x1*x2 - 0.03997407530678049362015296264*d3*d3*x1*x2 + 0.024402581235756294522944240729*d1*d1*x2*x2 - 0.222114475288566335001279698828*d1*d2*x2*x2 - 0.070083679749321594599200731887*d2*d2*x2*x2 - 0.070373642721913270922977661263*d1*d3*x2*x2 + 0.212390502375371038693795923176*d2*d3*x2*x2 + 0.045681098513565300076256491157*d3*d3*x2*x2 - 0.072474437917606222610888814369*x3 + 0.096892389337267175754518723416*a1*x3 - 0.180731364773115439193017446891*a2*x3 - 0.050635294011626415235472642593*a3*x3 - 0.259163690501218026568380442236*d1*d1*x3 + 0.925239462265199773426373099620*d1*d2*x3 - 0.744711358171274070848279050367*d2*d2*x3 - 0.989661029068914242813271892572*d1*d3*x3 + 2.465162599505184416360214409803*d2*d3*x3 + 1.076349486590098320027548306973*d3*d3*x3 + 0.086874253639862171565810308023*d1*d1*x1*x3 - 0.104705772815694026866281524260*d1*d2*x1*x3 - 0.074086393256211854682607813785*d2*d2*x1*x3 - 0.109549800628543333271213572829*d1*d3*x1*x3 + 0.299234827261588848586980562868*d2*d3*x1*x3 - 0.012787860383650316883202494238*d3*d3*x1*x3 + 0.052496121040573332345456860613*d1*d1*x2*x3 + 0.141038830267154797032348064791*d1*d2*x2*x3 - 0.162990092520280677425210758093*d2*d2*x2*x3 - 0.162293256236750564441328001591*d1*d3*x2*x3 - 0.195008609673189516011514471160*d2*d3*x2*x3 + 0.215486213560854009770667618706*d3*d3*x2*x3 + 0.017422202182328096778704276250*d1*d1*x3*x3 - 0.049398482411402199802472001525*d1*d2*x3*x3 + 0.031525476435001261781064160893*d2*d2*x3*x3 + 0.084558356183731783925946503176*d1*d3*x3*x3 - 0.165875343138965898937412459519*d2*d3*x3*x3 - 0.048947678617329358559768437144*d3*d3*x3*x3
    val f7 = -1.488897059003556983980038303505 + 2.231008647776575593013603793224*a1 + 2.725291869862966676052451475698*a2 + 0.212799634660929548601870919810*a3 - 2.209982345093386692405531459342*d1*d1 + 0.030994434108215905304756763456*d1*d2 + 3.335570361553556165071334276859*d2*d2 + 3.598167411350792771054448390770*d1*d3 + 4.913294640127032214864331377855*d2*d3 + 0.363309042543387511314235485988*d3*d3 + 0.756515902585150740496634712017*x1 + 0.516763360344266341395218501919*a1*x1 - 0.425581677878258237415005311197*a2*x1 - 1.456357108965599051374290448397*a3*x1 + 1.353717735220151431657722074917*d1*d1*x1 + 4.031607234221220787202938836859*d1*d2*x1 + 0.332510092864962844688601418028*d2*d2*x1 + 2.960668148934817772095406192571*d1*d3*x1 - 1.883172329603812663122548249203*d2*d3*x1 - 2.442743730670265016842958204964*d3*d3*x1 + 0.432127109117932580548716379753*d1*d1*x1*x1 - 0.194060966656687829055645099018*d1*d2*x1*x1 - 0.133265863648350747275113822794*d2*d2*x1*x1-1.11178704250731598970327054725*d1*d3*x1*x1-0.54337524569961318554234035822*d2*d3*x1*x1 - 0.298861245469581833273602556959*d3*d3*x1*x1 - 0.915447867590674481378524188076*x2 + 0.979356192114714329448542759071*a1*x2 + 0.509999534276701002086397031941*a2*x2 - 0.081733999841299775221261644903*a3*x2 - 1.878553039484931643105160316130*d1*d1*x2 + 1.426436135979090345939110777803*d1*d2*x2 + 2.875101726855450452354976515339*d2*d2*x2 + 2.279065593403253514430085645051*d1*d3*x2 + 1.694893748163785165822675081497*d2*d3*x2 - 0.081100819779844327871292011133*d3*d3*x2 + 0.608539009734964413828393589553*d1*d1*x1*x2 + 1.333243909060703205824395225273*d1*d2*x1*x2 - 0.191799883979898752150315381086*d2*d2*x1*x2 + 0.867551484145460615237108528126*d1*d3*x1*x2 - 1.240663204977234087412526171069*d2*d3*x1*x2 - 0.41673912575506566167807820846*d3*d3*x1*x2 + 0.398776343638168214398200160836*d1*d1*x2*x2 + 0.601215867513740551380598083453*d1*d2*x2*x2 + 0.421223345721378300885598455287*d2*d2*x2*x2 + 0.302245932953305328923357233825*d1*d3*x2*x2 + 0.072557157530085356140831652819*d2*d3*x2*x2 - 0.022447002083210086487398294450*d3*d3*x2*x2 + 0.411669837537925152706111485602*x3 + 0.955716043912293218765024039026*a1*x3 + 0.720404036476463017978100334902*a2*x3 + 0.373317805550967797323118141668*a3*x3 - 1.024562336298115457995879996408*d1*d1*x3 - 0.530756986842020966900841791423*d1*d2*x3 - 0.207619374798532911719868425485*d2*d2*x3 + 3.174642699925668684170198846120*d1*d3*x3 + 3.947400955531208187501964256803*d2*d3*x3 + 0.820511873558723217009636936291*d3*d3*x3 + 0.781154459306741308474621247886*d1*d1*x1*x3 + 0.709978611379942145622563916951*d1*d2*x1*x3 + 0.395698073639730320682548810010*d2*d2*x1*x3 + 1.003889779858533981814412439195*d1*d3*x1*x3 + 0.480574557355608905065232551215*d2*d3*x1*x3 - 1.176852532946471629157170057897*d3*d3*x1*x3 + 0.423326212185526953458614690643*d1*d1*x2*x3 + 0.086755867906055765871150653432*d1*d2*x2*x3 + 0.391147200149166947817637259490*d2*d2*x2*x3 + 0.956231875537754091917291147594*d1*d3*x2*x3 + 0.977800301534290644162803265035*d2*d3*x2*x3 + 0.032179012036360005640977431152*d3*d3*x2*x3 + 0.033350765479764366150516218916*d1*d1*x3*x3 - 0.407154900857052722324952984435*d1*d2*x3*x3 - 0.287957482073027553610484632492*d2*d2*x3*x3 + 0.809541109554010660779913313431*d1*d3*x3*x3 + 0.470818088169527829401508705401*d2*d3*x3*x3 + 0.321308247552791919761000851409*d3*d3*x3*x3

    f1 * f1 + f2*f2 + f3 * f3 + f4 * f4 + f5 * f5 + f6 * f6 + f7*f7 + (a1 * d1 + a2 * d2 + a3 *d3) * (a1 * d1 + a2 * d2 + a3 *d3) + (d1*d1 + d2*d2 + d3*d3 - 1) * (d1*d1 + d2*d2 + d3*d3 - 1)
  }
}

object Solvefunction {
  private val threadNumber = 5
  private val populationLengthPerThread = 20
  private val populationLength = threadNumber * populationLengthPerThread
  private val bestList = Vector()
  private val discretizationSize = 40
  private val random = new Random()
  private val loop = 1000
  private val randMin = -10d
  private val randMax = 10d
  private val cglsIndex = 8
  private var loopcondition = true
  private var functionsolution = Set((0.0,0.0))

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Inverse Problem")
    val sc = new SparkContext(conf)
    val staticPopulation = CEAlgorithm.initPopulation(populationLength, 9, randMin, randMax)
    var subPopulationbList: List[SubPopulation] = SubPopulationList.generateList(staticPopulation, threadNumber, populationLength, populationLengthPerThread)
    while (loopcondition) {
      var subPopulationListRDD: RDD[SubPopulation] = sc.parallelize(subPopulationbList)
      //generate new sub population list
      val newSubPopulationArray: Array[SubPopulation] = subPopulationListRDD.map(s =>{
        val subPopulation = s.subPopulation
        val newsubPopulation = subPopulation.zipWithIndex.map {row =>
          row._1.zipWithIndex.map{col =>
            //evaluate individual node
            val fitnessValue = Functionfitness.calculatefitness(col._1)
            var individual = new Individual(col._1.individualProperites, fitnessValue)
            //produce offspring
            var offSprings = CEAlgorithm.produceOffSpring(row._2, col._2, subPopulation, randMin, randMax)
            //random choosen two offSprings
            offSprings = random.shuffle(offSprings).slice(0, 2)
            offSprings.foreach{offspring =>
              val offSpringFitness =Functionfitness.calculatefitness(offspring)
              if (offSpringFitness < fitnessValue){
                individual = new Individual(offspring.individualProperites, offSpringFitness)
              }
            }
            individual
          }
        }
        new SubPopulation(newsubPopulation, s.thread)
      }
      ).collect()
      var newStaticPopulation = Array.ofDim[Individual](populationLength, populationLength)
      newSubPopulationArray.foreach{subPopulation =>
        val thread = subPopulation.thread
        for (i <- 0 until populationLength){
          for (j <- thread * populationLengthPerThread until (thread + 1) * populationLengthPerThread){
            if(thread == 0){
              newStaticPopulation(i)(j) = subPopulation.subPopulation(i)(j)
            }else{
              newStaticPopulation(i)(j) = subPopulation.subPopulation(i)(j - thread*populationLengthPerThread + 1)
            }
          }
        }
      }
      val tempStaticPopulation = newStaticPopulation.map(row => row.toVector).toVector
      subPopulationbList = SubPopulationList.generateList(tempStaticPopulation, threadNumber, populationLength, populationLengthPerThread)
      val bestindividual = new BestFitness(tempStaticPopulation).bestFitnessIndividual
      val bestfitness = bestindividual.fitness
      if (bestfitness < 0.01)
        functionsolution += (((math floor bestindividual.individualProperites(0) * 100) / 100, (math floor bestindividual.individualProperites(1) * 100) / 100))
        if (functionsolution.size==10)
          loopcondition = false
      print(functionsolution)
//      val tempStaticPopulation = newStaticPopulation.map(row => row.toVector).toVector
//      subPopulationbList = SubPopulationList.generateList(tempStaticPopulation, threadNumber, populationLength, populationLengthPerThread)
//      new BestFitness(tempStaticPopulation).printBestInfo
    }
  }

}