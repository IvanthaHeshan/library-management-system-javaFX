package Controllers.AddMember;

import model.Member;

import java.util.ArrayList;
import java.util.List;

public class AddMemberController {

    private static final List<Member> memberList = new ArrayList<>();

    public void addMember(Member member) {
        memberList.add(member);
    }

    public boolean isDuplicateMemberId(String memberId) {
        for (Member member : memberList) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean isValidPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        String cleaned = phone.replace("-", "").replace(" ", "");
        return cleaned.matches("\\d{10}");
    }

    public static List<Member> getMemberList() {
        return memberList;
    }

    public static int getTotalMemberCount() {
        return memberList.size();
    }
}