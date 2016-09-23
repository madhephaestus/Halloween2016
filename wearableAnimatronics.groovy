import eu.mihosoft.vrl.v3d.parametrics.*;
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;

LengthParameter thickness 		= new LengthParameter("Material Thickness",3.15,[10,1])
LengthParameter headDiameter 		= new LengthParameter("Head Dimeter",100,[200,50])
LengthParameter snoutLen 		= new LengthParameter("Snout Length",63,[200,50])
LengthParameter jawHeight 		= new LengthParameter("Jaw Height",32,[200,10])
LengthParameter leyeDiam 		= new LengthParameter("Left Eye Diameter",35,[headDiameter.getMM()/2,29])
LengthParameter reyeDiam 		= new LengthParameter("Right Eye Diameter",35,[headDiameter.getMM()/2,29])
LengthParameter eyeCenter 		= new LengthParameter("Eye Center Distance",headDiameter.getMM()/2,[headDiameter.getMM(),headDiameter.getMM()/2])
StringParameter servoSizeParam 			= new StringParameter("hobbyServo Default","towerProMG91",Vitamins.listVitaminSizes("hobbyServo"))
StringParameter boltSizeParam 			= new StringParameter("Bolt Size","M3",Vitamins.listVitaminSizes("capScrew"))

headDiameter.setMM(180)
snoutLen.setMM(150)
eyeCenter.setMM(100)
leyeDiam.setMM(60)
reyeDiam.setMM(60)

//def headParts  = (ArrayList<CSG> )ScriptingEngine.gitScriptRun("https://gist.github.com/e67b5f75f23c134af5d5054106e3ec40.git", "AnimatronicHead.groovy" ,  null )

return ScriptingEngine.gitScriptRun("https://github.com/madhephaestus/Halloween2016.git", "KevinHarringtonScan.stl" ,  null )
		.movey(-400)
		.movex(-60)
		.rotx(-25)
		.rotz(185)

//headParts.add(scannedHead)
//return headParts