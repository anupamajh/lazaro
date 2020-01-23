//
//  KBRow.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 21/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct KBRow: View {
    @State var kb:KB
    @ObservedObject var getKbAuthorPicService:GetKBAuthorPicService
    
    var body: some View {
        VStack{
            HStack{
                ZStack{
                    VStack{
                        self.getKbAuthorPicService.kbAuthorPic!
                        .resizable()
                        .clipShape(Circle())
                        Spacer()
                        
                    }.frame(width:45, height: 45)
                    .overlay(Circle().stroke(Color.white, lineWidth: 1))
                    if(self.getKbAuthorPicService.isLoading){
                        ActivityIndicator(shouldAnimate: .constant(true), style: .medium)
                    }
                }.padding()
                    .frame(alignment:.top)
                VStack{
                    HStack{
                        Text(self.kb.topicTitle!)
                            .font(Fonts.RobotRegular)
                        .bold()
                        .foregroundColor(Color("greyishBrownFour"))
                        Spacer()
                    }
                    HStack{
                        Text("Author : \(self.kb.authorName!)")
                            .font(Fonts.RobotSectionTitle)
                        .foregroundColor(Color("greyishBrownFour"))
                        Spacer()
                    }
                    
                    HStack{
                        Text(self.kb.creationTime!.convetToDateFromMySQL())
                            .font(Fonts.RobotSectionTitle)
                            .foregroundColor(Color("greyishBrownFour"))
                        Spacer()
                    }.padding(.vertical)
                    
                    HStack{
                        Text(self.kb.topicNarration!)
                            .font(Fonts.RobotSectionTitle)
                            .foregroundColor(Color("greyishBrownFour"))
                            .multilineTextAlignment(.leading)
                            .lineLimit(2)
                        Spacer()
                    }.padding(.trailing)
                }
                .padding()
                VStack{
                    Button(action:{
                        
                    }){
                        GuestJiniRoundButtonSystemImage(systemImage: "chevron.right")
                    }
                }.padding(.trailing)
            }
            Divider().padding(.bottom)

        }.frame(alignment:.top)
     }
}
    
    struct KBRow_Previews: PreviewProvider {
        static var previews: some View {
            KBRow(kb: KB(),getKbAuthorPicService: GetKBAuthorPicService(viewRouter: ViewRouter(), kbId: ""))
        }
}
