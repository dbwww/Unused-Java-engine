package models;

public class RawModelData {

	int vaoID;
	int vertexCount;
	

	public RawModelData(int vaoID, int vertexCount) {
		
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
}
