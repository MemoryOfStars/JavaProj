import java.io.*;
import java.util.*;

/**
	可以为这个类添加额外的方法及数据成员.
	ID就是指学号, 下面的作者一定要写上你的名字和学号
	作业中出现的示范数据abdc001需要改成学生的学号数据
	@author  	YOUR NAME and ID
	@version	THE DATE
**/


public class TextZip {

	//ID, 该学号的值需要修改!
	private static final String ID = "abdc001";	
	
	
  /**
	* This method generates the huffman tree for the text: "abracadabra!"
	*
	* @return the root of the huffman tree
	*/
	
	public static TreeNode abracadbraTree() {
		TreeNode n0 = new TreeNode(new CharFreq('!', 1));
		TreeNode n1 = new TreeNode(new CharFreq('c', 1));
		TreeNode n2 = new TreeNode(new CharFreq('\u0000', 2), n0, n1);
		TreeNode n3 = new TreeNode(new CharFreq('r', 2));
		TreeNode n4 = new TreeNode(new CharFreq('\u0000', 4), n3, n2);
		TreeNode n5 = new TreeNode(new CharFreq('d', 1));
		TreeNode n6 = new TreeNode(new CharFreq('b', 2));
		TreeNode n7 = new TreeNode(new CharFreq('\u0000', 3), n5, n6);
		TreeNode n8 = new TreeNode(new CharFreq('\u0000', '7'), n7, n4);
		TreeNode n9 = new TreeNode(new CharFreq('a', 5));
		TreeNode n10 = new TreeNode(new CharFreq('\u0000', 12), n9, n8);
		return n10;
	}

  /**
	* This method decompresses a huffman compressed text file.  The compressed
	* file must be read one bit at a time using the supplied BitReader, and
	* then by traversing the supplied huffman tree, each sequence of compressed
	* bits should be converted to their corresponding characters.  The
	* decompressed characters should be written to the FileWriter
	*
	* @param  br      the BitReader which reads one bit at a time from the
	*                 compressed file
	*         huffman the huffman tree that was used for compression, and
	*                 hence should be used for decompression
	*         fw      a FileWriter for storing the decompressed text file
	*/
	public static void decompress(BitReader br, TreeNode huffman, FileWriter fw) throws Exception {//解压
		
		// IMPLEMENT THIS METHOD
		TreeNode p  = huffman;
		for(;br.hasNext();){
			if(p.getLeft() == null&& p.getRight()==null){
				if(((CharFreq)p.getItem()).getChar()== ' ')fw.write("\r");
				else fw.write(((CharFreq)p.getItem()).getChar());
				if(((CharFreq)p.getItem()).getChar()== ' ')
					System.out.print("\r");
				else System.out.print(((CharFreq)p.getItem()).getChar());
				p = huffman;
				}
			else if(br.next() == false)p = p.getLeft();
			else p = p.getRight();
			
		}
	}//把br通过huffman输出到fw中
	
   /**
	* This method traverses the supplied huffman tree and prints out the
	* codes associated with each character
	*
	* @param  t    the root of the huffman tree to be traversed
	*         code a String used to build the code for each character as
	*              the tree is traversed recursively
	*/
	public static void traverse(TreeNode t, String code) {

		// IMPLEMENT THIS METHOD
		if(t.getLeft() == null&&t.getRight() ==null){
			System.out.println(((CharFreq)t.getItem()).getChar() + ":" + code);
		}
		if(t.getLeft() != null){
			traverse(t.getLeft(),code + '0');
		}
		if(t.getRight() != null){
			traverse(t.getRight(),code + '1');
		}
	}
	
  /**
	* This method removes the TreeNode, from an ArrayList of TreeNodes,  which
	* contains the smallest item.  The items stored in each TreeNode must
	* implement the Comparable interface.
	* The ArrayList must contain at least one element.
	*
	* @param  a an ArrayList containing TreeNode objects
	*
	* @return the TreeNode in the ArrayList which contains the smallest item.
	*         This TreeNode is removed from the ArrayList.
	*/
	public static TreeNode removeMin(ArrayList<?> a) {
		int minIndex = 0;
		for (int i = 0; i < a.size(); i++) {
			TreeNode ti = (TreeNode)a.get(i);
			TreeNode tmin = (TreeNode)a.get(minIndex);
			if (((Comparable<Object>)(ti.getItem())).compareTo(tmin.getItem()) < 0)
				minIndex = i;
		}
		TreeNode n = (TreeNode)a.remove(minIndex);
		return n;
	}

  /**
	* This method counts the frequencies of each character in the supplied
	* FileReader, and produces an output text file which lists (on each line)
	* each character followed by the frequency count of that character.  This
	* method also returns an ArrayList which contains TreeNodes.  The item stored
	* in each TreeNode in the returned ArrayList is a CharFreq object, which
	* stores a character and its corresponding frequency
	*
	* @param  fr the FileReader for which the character frequencies are being
	*            counted
	*         pw the PrintWriter which is used to produce the output text file
	*            listing the character frequencies
	*
	* @return the ArrayList containing TreeNodes.  The item stored in each
	*         TreeNode is a CharFreq object.
	*/
	public static ArrayList<?> countFrequencies(FileReader fr, PrintWriter pw) throws Exception {//fr读字符串，pw写入文件，是字符的出现频率

		// IMPLEMENT THIS METHOD
		char ch;
		ArrayList<TreeNode> cs = new ArrayList<TreeNode>();
		//ArrayList<Character> rec = new ArrayList<Character>();
		while((ch = (char)fr.read()) != (char)-1){
			boolean f = true;
			for(int i = 0;i < cs.size();i++){
				if(((CharFreq)cs.get(i).getItem()).getChar() == ch){((CharFreq)cs.get(i).getItem()).plusFreq();f = false;}
			}
			if(f)cs.add(new TreeNode(new CharFreq(ch,1)));
		}
		for(TreeNode cc : cs){
			String s = ((CharFreq)cc.getItem()).getChar() +  " "  +  (((CharFreq)cc.getItem()).getFreq()) + '\n';
			pw.write(s);
			pw.flush();
		}
		return cs;
				
	}

  /**
	* This method builds a huffman tree from the supplied ArrayList of TreeNodes.
	* Initially, the items in each TreeNode in the ArrayList store a CharFreq object.
	* As the tree is built, the smallest two items in the ArrayList are removed,
	* merged to form a tree with a CharFreq object storing the sum of the frequencies
	* as the root, and the two original CharFreq objects as the children.  The right
	* child must be the second of the two elements removed from the ArrayList (where
	* the ArrayList is scanned from left to right when the minimum element is found).
	* When the ArrayList contains just one element, this will be the root of the
	* completed huffman tree.
	*
	* @param  trees the ArrayList containing the TreeNodes used in the algorithm
	*               for generating the huffman tree
	*
	* @return the TreeNode referring to the root of the completed huffman tree
	*/
	public static ArrayList<?> sortedTree(ArrayList trees) {
		for(int i = 0;i < trees.size()-1;i++)
		{
			for(int j = 0;j < trees.size()-i-1;j++)
			{
				if(((CharFreq)(((TreeNode)(trees.get(j))).getItem())).getFreq() > ((CharFreq)(((TreeNode)(trees.get(j+1))).getItem())).getFreq()){
					Object ob = trees.get(j);
					trees.set(j, trees.get(j+1));
					trees.set(j+1,ob);
				}
			}
		}
		return trees;
	}
	public static TreeNode buildTree(ArrayList trees) throws IOException {

		// IMPLEMENT THIS METHOD
		
		for(;trees.size() > 1;)
		{
			sortedTree(trees);
			TreeNode tn1 = new TreeNode(new CharFreq(((CharFreq)((TreeNode)trees.get(0)).getItem()).getChar(),((CharFreq)((TreeNode)trees.get(0)).getItem()).getFreq()));
			tn1.setLeft(((TreeNode)trees.get(0)).getLeft());
			tn1.setRight(((TreeNode)trees.get(0)).getRight());
			trees.remove(0);
			TreeNode tn2 = new TreeNode(new CharFreq(((CharFreq)((TreeNode)trees.get(0)).getItem()).getChar(),((CharFreq)((TreeNode)trees.get(0)).getItem()).getFreq()));
			tn2.setLeft(((TreeNode)trees.get(0)).getLeft());
			tn2.setRight(((TreeNode)trees.get(0)).getRight());
			trees.remove(0);
			TreeNode tn3 = new TreeNode(new CharFreq('\u0000',(((CharFreq)tn1.getItem()).getFreq()) + (((CharFreq)tn2.getItem()).getFreq())));
			tn3.setLeft(tn1);
			tn3.setRight(tn2);
			trees.add(tn3);
		}
		return (TreeNode)trees.get(0);
	}

  /**
	* This method compresses a text file using huffman encoding.  Initially, the
	* supplied huffman tree is traversed to generate a lookup table of codes for
	* each character.  The text file is then read one character at a time, and
	* each character is encoded by using the lookup table.  The encoded bits for
	* each character are written one at a time to the specified BitWriter.
	*
	* @param  fr      the FileReader which contains the text file to be encoded
	*         huffman the huffman tree that was used for compression, and
	*                 hence should be used for decompression
	*         bw      the BitWriter used to write the compressed bits to file
	*/
	public static void retCode(TreeNode r,String code,ArrayList<charStr> alcs){
		if(r != null){
			if(r.getLeft() != null){
				retCode(r.getLeft(), code + '0', alcs);
			}
			if(r.getLeft() != null){
				retCode(r.getRight(), code + '1', alcs);
			}
			if(r.getLeft() == null && r.getRight() == null){
				alcs.add(new charStr(((CharFreq)r.getItem()).getChar(),code));
			}
		}
	}
	public static void compress(FileReader fr, TreeNode huffman, BitWriter bw) throws Exception {

		// IMPLEMENT THIS METHOD
		char ch;
		FileReader ffr = new FileReader("F:\\大二小学期\\第二次作业要求\\第二次作业要求\\huffman\\huffman\\file.txt");
		PrintWriter pw = new PrintWriter("F:\\大二小学期\\第二次作业要求\\第二次作业要求\\huffman\\huffman\\a.txz");
		ArrayList<?> tns = countFrequencies(fr,pw);
		//TreeNode root = buildTree(tns);//建树
		
		ArrayList<charStr> alcs = new ArrayList<charStr>();
		retCode(huffman,"",alcs);//获得每一个字符的编码
		while((ch = (char)ffr.read())!= (char)-1){
			for(int i = 0;i < alcs.size();i++){
				if(alcs.get(i).ch == ch){
					String tem = alcs.get(i).code;
					for(int j = 0;j < tem.length();j++){
						if(tem.charAt(j) == '1'){bw.writeBit(true);System.out.print(1);}//
						else if(tem.charAt(j) == '0')
							{bw.writeBit(false);System.out.print(0);}
					}
				}
			}
		}
	}

  /**
	* This method reads a frequency file (such as those generated by the
	* countFrequencies() method) and initialises an ArrayList of TreeNodes
	* where the item of each TreeNode is a CharFreq object storing a character
	* from the frequency file and its corresponding frequency.  This method provides
	* the same functionality as the countFrequencies() method, but takes in a
	* frequency file as parameter rather than a text file.
	*
	* @param  inputFreqFile the frequency file which stores characters and their
	*                       frequency (one character per line)
	*
	* @return the ArrayList containing TreeNodes.  The item stored in each
	*         TreeNode is a CharFreq object.
	*/
	public static ArrayList<?> readFrequencies(String inputFreqFile) throws Exception {

		File f = new File(inputFreqFile);
		BufferedReader fr = new BufferedReader(new FileReader(f));
		ArrayList<TreeNode> cr = new ArrayList<TreeNode>();
		String ss = fr.readLine();
		for(;ss!=null;)
		{	
			char ch;int freq=0;boolean fl = false;
			for(;ss.length() == 0;ss = fr.readLine())fl = true;
			if(fl){ch = '\n';fl = false;}
			ch= ss.charAt(0);
			if(ss.length() == 2) freq= Integer.parseInt(ss.substring(1));
			else if(ss.length() > 2){
				//ss = fr.readLine();
				freq = Integer.parseInt(ss.substring(2));
			}
			else{
				ss = fr.readLine();
				freq = Integer.parseInt(ss.substring(0));
			}
			cr.add(new TreeNode(new CharFreq(ch,freq)));
			ss = fr.readLine();
		}
		return cr;
		// IMPLEMENT THIS METHOD
	}

	/* This TextZip application should support the following command line flags:

	QUESTION 2 PART 1
	=================
		 -a : this uses a default prefix code tree and its compressed
		      file, "a.txz", and decompresses the file, storing the output
		      in the text file, "a.txt".  It should also print out the size
		      of the compressed file (in bytes), the size of the decompressed
		      file (in bytes) and the compression ratio

	QUESTION 2 PART 2
	=================
		 -f : given a text file (args[1]) and the name of an output frequency file
		      (args[2]) this should count the character frequencies in the text file
		      and store these in the frequency file (with one character and its
		      frequency per line).  It should then build the huffman tree based on
		      the character frequencies, and then print out the prefix code for each
		      character

	QUESTION 2 PART 3
	=================
		 -c : given a text file (args[1]) and the name of an output frequency file
		      (args[2]) and the name of the output compressed file (args[3]), this
		      should compress file

	QUESTION 2 PART 4
	=================
		 -d : given a compressed file (args[1]) and its corresponding frequency file
		      (args[2]) and the name of the output decompressed text file (args[3]),
		      this should decompress the file

	*/

	public static void main(String[] args) throws Exception {

		if (args[0].equals("-a")) {
			BitReader br = new BitReader("F:\\大二小学期\\第二次作业要求\\第二次作业要求\\huffman\\huffman\\a.txz");
			FileWriter fw = new FileWriter("F:\\大二小学期\\第二次作业要求\\第二次作业要求\\huffman\\huffman\\a.txt");

			// Get the default prefix code tree
			TreeNode tn = abracadbraTree();

			// Decompress the default file "a.txz"
			decompress(br, tn, fw);

			// Close the ouput file
			fw.close();
			// Output the compression ratio
			// Write your own implementation here.
			
		}

		else if (args[0].equals("-f")) {
			FileReader fr = new FileReader(args[1]);
			PrintWriter pw = new PrintWriter(new FileWriter(args[2]));

			// Calculate the frequencies
			ArrayList<?> trees = countFrequencies(fr, pw);

			// Close the files
			fr.close();
			pw.close();

			// Build the huffman tree
			TreeNode n = buildTree(trees);

			// Display the codes
			traverse(n, "");
		}


		else if (args[0].equals("-c")) {

			FileReader fr = new FileReader(args[1]);//读字符串
			PrintWriter pw = new PrintWriter(new FileWriter(args[2]));//写入字符频率
			ArrayList<?> trees = countFrequencies(fr, pw);

			fr.close();
			pw.close();

			

			TreeNode n = buildTree(trees);
			traverse(n, "");
			BitWriter bw = new BitWriter(args[3]);//写入压缩编码
			// IMPLEMENT NEXT 
			// Finish the compress function here
			compress(new FileReader(args[1]),n,bw);
			
			bw.close();
			// then output the compression ratio
			// Write your own implementation here.
			BitReader br = new BitReader(args[3]);
			System.out.println();
			while(br.hasNext()){
				if(br.next())
					System.out.print("1");
				else
					System.out.print("0");
			}
			


		}

		else if (args[0].equals("-d")) {
			ArrayList<?> a = readFrequencies(args[2]);
			TreeNode tn = buildTree(a);
			BitReader br = new BitReader(args[3]);
			FileWriter fw = new FileWriter(args[1]);
			decompress(br, tn, fw);
			fw.close();

			// Output the compression ratio
			// Write your own implementation here.


		
		}
	}
}
