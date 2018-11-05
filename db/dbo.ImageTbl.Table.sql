USE [KumuApp]
GO
/****** Object:  Table [dbo].[ImageTbl]    Script Date: 11/4/2018 5:04:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImageTbl](
	[ImageID] [bigint] IDENTITY(1,1) NOT NULL,
	[FormID] [bigint] NOT NULL,
	[ImageName] [nvarchar](50) NOT NULL,
	[Latitude] [decimal](10, 5) NOT NULL,
	[Longitude] [decimal](10, 5) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ImageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
