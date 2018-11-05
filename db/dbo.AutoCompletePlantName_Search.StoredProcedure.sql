USE [KumuApp]
GO
/****** Object:  StoredProcedure [dbo].[AutoCompletePlantName_Search]    Script Date: 11/4/2018 5:04:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Joshua Nishiguchi
-- Create date: 11/4/2018
-- Description:	When a user is searching for a specific plant, it'll
--				try to autocomplete on the TaxaName and CommonName
-- =============================================
CREATE PROCEDURE [dbo].[AutoCompletePlantName_Search]
	@PlantName nvarchar(255)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	CREATE TABLE #TempPlantNames (
		PlantName nvarchar(255),
		TaxaNameIndicator bit
	)
	
	INSERT INTO #TempPlantNames (
		PlantName,
		TaxaNameIndicator
	)
	SELECT PlantTaxaName, 1
	FROM PlantMasterTbl

	/*INSERT INTO #TempPlantNames (
		PlantName,
		TaxaNameIndicator
	)
	SELECT PlantCommonName, 0
	FROM PlantMasterTbl
	WHERE PlantCommonName <> '' AND PlantCommonName <> 'No common name'
	*/

	--Use taxaname first if possible
	SELECT TOP 30 PlantName
	FROM #TempPlantNames 
	GROUP BY PlantName, TaxaNameIndicator
	ORDER BY DIFFERENCE(PlantName, @PlantName) DESC, TaxaNameIndicator DESC

END
GO
