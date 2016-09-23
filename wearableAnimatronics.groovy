import eu.mihosoft.vrl.v3d.parametrics.*;
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;

LengthParameter thickness 		= new LengthParameter("Material Thickness",3.15,[10,1])
LengthParameter headDiameter 		= new LengthParameter("Head Dimeter",100,[200,50])
LengthParameter upperHeadDiam 	= new LengthParameter("Upper Head Height",20,[300,0])
LengthParameter snoutLen 		= new LengthParameter("Snout Length",63,[200,50])
LengthParameter jawHeight 		= new LengthParameter("Jaw Height",85,[200,10])
LengthParameter leyeDiam 		= new LengthParameter("Left Eye Diameter",35,[headDiameter.getMM()/2,29])
LengthParameter reyeDiam 		= new LengthParameter("Right Eye Diameter",35,[headDiameter.getMM()/2,29])
LengthParameter eyeCenter 		= new LengthParameter("Eye Center Distance",headDiameter.getMM()/2,[headDiameter.getMM(),headDiameter.getMM()/2])
StringParameter servoSizeParam 			= new StringParameter("hobbyServo Default","towerProMG91",Vitamins.listVitaminSizes("hobbyServo"))
StringParameter boltSizeParam 			= new StringParameter("Bolt Size","M3",Vitamins.listVitaminSizes("capScrew"))

servoSizeParam.setStrValue("hv6214mg")
headDiameter.setMM(400)
snoutLen.setMM(300)
eyeCenter.setMM(250)
leyeDiam.setMM(120)
reyeDiam.setMM(120)
jawHeight.setMM(85)
upperHeadDiam.setMM(50)

def headParts  = (ArrayList<CSG> )ScriptingEngine.gitScriptRun("https://gist.github.com/e67b5f75f23c134af5d5054106e3ec40.git", "AnimatronicHead.groovy" ,  null )
println "Loading head"
CSG scannedHead =  ScriptingEngine.gitScriptRun("https://github.com/madhephaestus/Halloween2016.git", "KevinHarringtonScan.stl" ,  null )
		.movey(-400)
		.movex(-60)
		.rotx(-23)
		.rotz(185)
		.movey(15)
		.movez(-65)
		.movex(75)
println "Making cutout"
CSG cutout = scannedHead
			.hull()
			.scale(1.02)
BowlerStudioController.addCsg(cutout)		
println "Performing cutout"

for(int i=3;i<6;i++){
	headParts.set(	i,
				headParts.get(i)
					.difference(cutout)
				)

}

headParts.add(scannedHead)
return headParts