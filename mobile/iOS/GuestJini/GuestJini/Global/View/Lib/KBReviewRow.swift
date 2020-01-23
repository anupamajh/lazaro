//
//  KBReviewRow.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct KBReviewRow: View {
    @State var kbReview:KBReview
    @State var reviewerImage:Image = Image(systemName: "person.fill")
    var body: some View {
        VStack{
            HStack{
                ZStack{
                    VStack{
                        self.reviewerImage
                        .resizable()
                        .clipShape(Circle())
                        Spacer()
                        
                    }.frame(width:45, height: 45)
                    .overlay(Circle().stroke(Color.white, lineWidth: 1))
//                    if(self.getKbAuthorPicService.isLoading){
//                        ActivityIndicator(shouldAnimate: .constant(true), style: .medium)
//                    }
                }.padding()
                    .frame(alignment:.top)
                VStack{
                                   HStack{
                                    Text(self.kbReview.reviewByName!)
                                           .font(Fonts.RobotRegular)
                                       .bold()
                                       .foregroundColor(Color("greyishBrownFour"))
                                       Spacer()
                                   }
                                   
                                   HStack{
                                    Text(self.kbReview.creationTime!.convetToDateFromMySQL())
                                           .font(Fonts.RobotSectionTitle)
                                           .foregroundColor(Color("greyishBrownFour"))
                                       Spacer()
                                   }
                                   
                                   HStack{
                                       Text(self.kbReview.reviewComment!)
                                           .font(Fonts.RobotSectionTitle) .foregroundColor(Color("greyishBrownFour"))
                                           .multilineTextAlignment(.leading)
                                           .lineLimit(100)
                                       Spacer()
                                   }.padding(.trailing)
                                    .padding(.top)
                               }
                               .padding()
                              
            }
        }
    }
}

struct KBReviewRow_Previews: PreviewProvider {
    static var previews: some View {
        KBReviewRow(kbReview:KBReview())
    }
}
