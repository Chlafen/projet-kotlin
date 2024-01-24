package com.example.nyt.model
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
sealed class ArticleType(val value: String) {
    object  AdventureSports: ArticleType( "Adventure Sports")
    object  ArtsLeisure: ArticleType( "Arts & Leisure")
    object  Arts: ArticleType( "Arts")
    object  Automobiles: ArticleType( "Automobiles")
    object  Blogs: ArticleType( "Blogs")
    object  Books: ArticleType( "Books")
    object  Booming: ArticleType( "Booming")
    object  BusinessDay: ArticleType( "Business Day")
    object  Business: ArticleType( "Business")
    object  Cars: ArticleType( "Cars")
    object  Circuits: ArticleType( "Circuits")
    object  Classifieds: ArticleType( "Classifieds")
    object  Connecticut: ArticleType( "Connecticut")
    object  CrosswordsGames: ArticleType( "Crosswords & Games")
    object  Culture: ArticleType( "Culture")
    object  DealBook: ArticleType( "DealBook")
    object  Dining: ArticleType( "Dining")
    object  Editorial: ArticleType( "Editorial")
    object  Education: ArticleType( "Education")
    object  Energy: ArticleType( "Energy")
    object  Entrepreneurs: ArticleType( "Entrepreneurs")
    object  Environment: ArticleType( "Environment")
    object  Escapes: ArticleType( "Escapes")
    object  FashionStyle: ArticleType( "Fashion & Style")
    object  Fashion: ArticleType( "Fashion")
    object  Favorites: ArticleType( "Favorites")
    object  Financial: ArticleType( "Financial")
    object  Flight: ArticleType( "Flight")
    object  Food: ArticleType( "Food")
    object  Foreign: ArticleType( "Foreign")
    object  Generations: ArticleType( "Generations")
    object  Giving: ArticleType( "Giving")
    object  GlobalHome: ArticleType( "Global Home")
    object  HealthFitness: ArticleType( "Health & Fitness")
    object  Health: ArticleType( "Health")
    object  HomeGarden: ArticleType( "Home & Garden")
    object  Home: ArticleType( "Home")
    object  Jobs: ArticleType( "Jobs")
    object  Key: ArticleType( "Key")
    object  Letters: ArticleType( "Letters")
    object  LongIsland: ArticleType( "Long Island")
    object  Magazine: ArticleType( "Magazine")
    object  MarketPlace: ArticleType( "Market Place")
    object  Media: ArticleType( "Media")
    object  MensHealth: ArticleType( "Men's Health")
    object  Metro: ArticleType( "Metro")
    object  Metropolitan: ArticleType( "Metropolitan")
    object  Movies: ArticleType( "Movies")
    object  Museums: ArticleType( "Museums")
    object  National: ArticleType( "National")
    object  Nesting: ArticleType( "Nesting")
    object  Obits: ArticleType( "Obits")
    object  Obituaries: ArticleType( "Obituaries")
    object  Obituary: ArticleType( "Obituary")
    object  OpEd: ArticleType( "OpEd")
    object  Opinion: ArticleType( "Opinion")
    object  Outlook: ArticleType( "Outlook")
    object  PersonalInvesting: ArticleType( "Personal Investing")
    object  PersonalTech: ArticleType( "Personal Tech")
    object  Play: ArticleType( "Play")
    object  Politics: ArticleType( "Politics")
    object  Regionals: ArticleType( "Regionals")
    object  Retail: ArticleType( "Retail")
    object  Retirement: ArticleType( "Retirement")
    object  Science: ArticleType( "Science")
    object  SmallBusiness: ArticleType( "Small Business")
    object  Society: ArticleType( "Society")
    object  Sports: ArticleType( "Sports")
    object  Style: ArticleType( "Style")
    object  SundayBusiness: ArticleType( "Sunday Business")
    object  SundayReview: ArticleType( "Sunday Review")
    object  SundayStyles: ArticleType( "Sunday Styles")
    object  TMagazine: ArticleType( "T Magazine")
    object  TStyle: ArticleType( "T Style")
    object  Technology: ArticleType( "Technology")
    object  Teens: ArticleType( "Teens")
    object  Television: ArticleType( "Television")
    object  TheArts: ArticleType( "The Arts")
    object  TheBusinessofGreen: ArticleType( "The Business of Green")
    object  TheCityDesk: ArticleType( "The City Desk")
    object  TheCity: ArticleType( "The City")
    object  TheMarathon: ArticleType( "The Marathon")
    object  TheMillennium: ArticleType( "The Millennium")
    object  TheNaturalWorld: ArticleType("The Natural World")
    object  TheUpshot: ArticleType( "The Upshot")
    object  TheWeekend: ArticleType( "The Weekend")
    object  TheYearinPictures: ArticleType(  "The Year in Pictures")
    object  Theater: ArticleType( "Theater")
    object  ThenNow: ArticleType( "Then & Now")
    object  ThursdayStyles :ArticleType( "Thursday Styles")
    object  TimesTopics :ArticleType( "Times Topics")
    object  Travel: ArticleType( "Travel")
    object  US: ArticleType( "U.S.")
    object  Universal: ArticleType( "Universal")
    object  Upshot: ArticleType( "Upshot")
    object  UrbanEye: ArticleType( "UrbanEye")
    object  Vacation: ArticleType( "Vacation")
    object  Washington: ArticleType( "Washington")
    object  Wealth: ArticleType( "Wealth")
    object  Weather: ArticleType( "Weather")
    object  WeekInReview: ArticleType( "Week in Review")
    object  Week: ArticleType( "Week")
    object  Weekend: ArticleType( "Weekend")
    object  Westchester: ArticleType( "Westchester")
    object  WirelessLiving: ArticleType( "Wireless Living")
    object  WomenHealth: ArticleType( "Women's Health")
    object  Working: ArticleType( "Working")
    object  Workplace: ArticleType( "Workplace")
    object  World: ArticleType( "World")
    object  YourMoney: ArticleType( "Your Money")
    object  All : ArticleType( "" )

}