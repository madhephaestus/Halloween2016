import eu.mihosoft.vrl.v3d.parametrics.*;
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;

LengthParameter thickness 		= new LengthParameter("Material Thickness",3.15,[10,1])
LengthParameter headDiameter 		= new LengthParameter("Head Dimeter",100,[200,50])
LengthParameter upperHeadDiam 	= new LengthParameter("Upper Head Height",20,[300,0])
LengthParameter snoutLen 		= new LengthParameter("Snout Length",63,[200,50])
LengthParameter jawHeight 		= new LengthParameter("Jaw Height",85,[200,10])
LengthParameter leyeDiam 		= new LengthParameter("Left Eye Diameter",35,[headDiameter.getMM()/2,29])
LengthParameter reyeDiam 		= new LengthParameter("Right Eye Diameter",35,[headDiameter.getMM()/2,29])
LengthParameter eyemechRadius		= new LengthParameter("Eye Mech Linkage",10,[20,5])
LengthParameter eyeCenter 		= new LengthParameter("Eye Center Distance",headDiameter.getMM()/2,[headDiameter.getMM(),headDiameter.getMM()/2])
StringParameter servoSizeParam 			= new StringParameter("hobbyServo Default","towerProMG91",Vitamins.listVitaminSizes("hobbyServo"))
StringParameter boltSizeParam 			= new StringParameter("Bolt Size","M3",Vitamins.listVitaminSizes("capScrew"))
LengthParameter boltLength		= new LengthParameter("Bolt Length",10,[18,10])

servoSizeParam.setStrValue("hv6214mg")
boltSizeParam.setStrValue("8#32")
headDiameter.setMM(300)
snoutLen.setMM(250)
eyeCenter.setMM(200)
leyeDiam.setMM(100)
reyeDiam.setMM(100)
jawHeight.setMM(85)
upperHeadDiam.setMM(40)
eyemechRadius.setMM(15)
thickness.setMM(6)
boltLength.setMM(27)

def headParts  = (ArrayList<CSG> )ScriptingEngine.gitScriptRun("https://gist.github.com/e67b5f75f23c134af5d5054106e3ec40.git", "AnimatronicHead.groovy" ,  [false] )
println "Loading head"
CSG scannedHead =  ScriptingEngine.gitScriptRun("https://github.com/madhephaestus/Halloween2016.git", "KevinHarringtonScan_moved.stl" ,  null )
				.movex(-40)
				.movez(-20)
println "Making cutout"
CSG cutout = scannedHead
			.hull()
			.scale(1.02)
BowlerStudioController.addCsg(cutout)		
println "Performing cutout"

for(int i=3;i<6;i++){
	def mfg = headParts.get(i).getManufactuing()
	CSG newPart = headParts.get(i)
					.difference(cutout)
	newPart.setManufactuing(mfg)
	headParts.set(	i,newPart)

}
println "Creating cutsheet"
ArrayList<CSG> sheetParts = new ArrayList<>()
for(int i=0;i<headParts.size()-10;i++){
	sheetParts.add(headParts.get(i))
}

def allParts = 	sheetParts.collect { it.prepForManufacturing() } 
CSG cutSheet = allParts.get(0).union(allParts)
headParts.add(cutSheet )
headParts.add(scannedHead)
return headParts