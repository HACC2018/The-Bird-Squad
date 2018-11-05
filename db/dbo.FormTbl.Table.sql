USE [KumuApp]
GO
/****** Object:  Table [dbo].[FormTbl]    Script Date: 11/4/2018 5:04:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FormTbl](
	[FormID] [bigint] IDENTITY(1,1) NOT NULL,
	[UID] [bigint] NOT NULL,
	[PlantType] [nvarchar](50) NOT NULL,
	[PlantTaxaName] [nvarchar](255) NOT NULL,
	[IslandID] [int] NOT NULL,
	[PopulationReferenceArea] [nvarchar](10) NOT NULL,
	[PopulationReferenceSite] [nvarchar](10) NOT NULL,
	[LocationNotes] [nvarchar](255) NULL,
	[NumberMaturePlants] [int] NULL,
	[NumberImmaturePlants] [int] NULL,
	[NumberSeedlings] [int] NULL,
	[PlantTagged] [bit] NULL,
	[Sex] [nvarchar](50) NULL,
	[HeightInMeters] [decimal](10, 4) NULL,
	[BasalDiameterCM] [decimal](10, 4) NULL,
	[Age] [nvarchar](50) NULL,
	[ReproductiveStatus] [nvarchar](50) NULL,
	[Vigor] [nvarchar](50) NULL,
	[AmountImmatureFruitCollected] [int] NULL,
	[AmountMatureFruitCollected] [int] NULL,
	[AmountCuttingsCollected] [int] NULL,
	[AmountAirLayersCollected] [int] NULL,
	[AmountFlowersCollected] [int] NULL,
	[PercPlantsVegetative] [int] NULL,
	[PercPlantsFlowerBuds] [int] NULL,
	[PercPlantsInFlower] [int] NULL,
	[PercPlantsImmatureFruit] [int] NULL,
	[PercPlantsMatureFruit] [int] NULL,
	[PercPlantsHealthy] [int] NULL,
	[PercPlantsModerate] [int] NULL,
	[PercPlantsPoor] [int] NULL,
	[PercPlantsDead] [int] NULL,
	[PercPlantsFullSun] [int] NULL,
	[PercPlantsPartialSun] [int] NULL,
	[PercPlantsPartialShade] [int] NULL,
	[PercPlantsDeepShade] [int] NULL,
	[OverstoryClosure] [nvarchar](80) NULL,
	[OverstoryHeight] [nvarchar](80) NULL,
	[UnderstoryClosure] [nvarchar](80) NULL,
	[SoilDranage] [nvarchar](80) NULL,
	[Topography] [nvarchar](50) NULL,
	[Aspect] [nvarchar](20) NULL,
	[AssociatedOverstorySpecies] [nvarchar](100) NULL,
	[AssociatedUnderstorySpecies] [nvarchar](100) NULL,
	[Substrate] [nvarchar](50) NULL,
	[Threats] [nvarchar](255) NULL,
	[ThreatNotes] [nvarchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[FormID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
