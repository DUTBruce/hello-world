
public class Student {
	public String id, name;
	public int age;

	// ��ʵ��sort�������������е�ѧ�������������������
	public static void sort(Student[] sa) { //�������� ��������
		Student temp;
		for(int i=1; i<sa.length; i++)
		{
			int j;
			for(j=0; j<i; j++)
			{
				if(sa[i].age < sa[j].age)
					break;
			}
			if(j!=i)
			{
				temp = sa[i];
				for(int k=i-1; k>=j; k--)
					sa[k+1] = sa[k];
				sa[j] = temp;
			}
		}
		
		
	}

	public String toString() {
		return "id = " + id + ", name = " + name + ", age = " + age;
	}

	public static void main(String[] args) {
		Student s1 = new Student();
		s1.id = "001";
		s1.name = "С��";
		s1.age = 18;

		Student s2 = new Student();
		s2.id = "002";
		s2.name = "С��";
		s2.age = 19;

		Student s3 = new Student();
		s3.id = "003";
		s3.name = "С��";
		s3.age = 17;

		Student s4 = new Student();
		s4.id = "004";
		s4.name = "С��";
		s4.age = 20;

		Student[] sa = { s1, s2, s3, s4 };

		sort(sa);

		for (int i = 0; i < sa.length; i++) {
			System.out.println(sa[i]);
		}

	}

}
