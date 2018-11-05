USE [KumuApp]
GO
/****** Object:  StoredProcedure [dbo].[GetImageFromForm]    Script Date: 11/4/2018 5:04:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Joshua Nishiguchi
-- Create date: 11/3/2018
-- Description:	Retrieves images from form
-- =============================================
CREATE PROCEDURE [dbo].[GetImageFromForm]
	@FormID bigint
AS
BEGIN
	SET NOCOUNT ON;

    SELECT ImageName, Latitude, Longitude
	FROM ImageTbl
	WHERE
		FormID = @FormID
END
GO
