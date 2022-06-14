CREATE DATABASE PolyShop
GO
USE PolyShop

-- Create Table 
-- 1. Categories
CREATE TABLE Categories(
	CategoryId CHAR(5) PRIMARY KEY NOT NULL,
	[Name] NVARCHAR(255) NOT NULL
)
-- 2. Products
CREATE TABLE Products(
	ProductId INT IDENTITY(1,1) PRIMARY KEY,
	[Name] NVARCHAR(255),
	[Image] NVARCHAR(MAX),
	Price FLOAT,
	CreateDate DATE DEFAULT GETDATE(),
	Available BIT DEFAULT 1,
	Quantity INT,
	CategoryId CHAR(5) NOT NULL,
	Discount FLOAT DEFAULT 0
)

-- 3. Orders (Status thể hiện admin đã duyệt hay duyệt hóa đơn, Avaiable thể hiện xóa hóa đơn)
CREATE TABLE Orders(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	Username VARCHAR(255),
	CreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
	[Address] NVARCHAR(255),
	Total FLOAT,
	[Status] BIT DEFAULT 0,
	Avaiable BIT DEFAULT 1,
	Delivery BIT DEFAULT 0,
    ApprovalDate DATE
)
UPDATE dbo.Orders SET ApprovalDate = GETDATE()
SELECT * FROM dbo.Orders
-- 4. OrderDetails
CREATE TABLE OrderDetails(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	ProductId INT NOT NULL,
	Price FLOAT NOT NULL,
	Quantity INT NOT NULL,
	OrderId INT NOT NULL
)
-- 5. Accounts
CREATE TABLE Accounts(
	Username VARCHAR(255) PRIMARY KEY,
	[Password] VARCHAR(100) NOT NULL,
	Fullname NVARCHAR(255),
	Email VARCHAR(255),
	Photo VARCHAR(255),
	Phone CHAR(10),
	Active BIT DEFAULT 1,
	[Admin] INT DEFAULT 0
)
--6. Size
CREATE TABLE Size(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	ProductId INT NOT NULL,
	Size VARCHAR(20) NOT NULL
	UNIQUE(ProductId, Size)
)
-- 7. Shipping (Status: true thể hiện giao thành công, false thể hiện chưa giao)
CREATE TABLE Shipping(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	OrderId INT,
	ShipperId VARCHAR(255),
	[Status] BIT DEFAULT 0
)
-- 8. Favorite
CREATE TABLE Favorites(
	IdFav INT IDENTITY(1,1) PRIMARY KEY,
	ProductId INT NOT NULL,
	Username VARCHAR(255) NOT NULL,
	DateLike DATE DEFAULT GETDATE() NOT NULL,
	IsLike BIT DEFAULT 1 NOT NULL
)
-- 9. Visitor
CREATE TABLE Visitor(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	DateVisit DATE DEFAULT GETDATE(),
	Username VARCHAR(255) NOT NULL
)
-- 10. PlaceReceipt
CREATE TABLE PlaceReceipt(
	Id INT IDENTITY (1,1) PRIMARY KEY,
	OrderId INT NOT NULL,
	Fullname NVARCHAR(255) NOT NULL,
	[Address] NVARCHAR(MAX) NOT NULL,
	Email VARCHAR(100) NOT NULL,
	Phone CHAR(10) NOT NULL,
	Note NVARCHAR(MAX)
)


SELECT * FROM dbo.Visitor
-- NOTE: Cần thêm UNIQUE(ProductId, Username)
-- Create FK - PK
-- 1. Product
ALTER TABLE dbo.Products ADD CONSTRAINT FK_Category_Id FOREIGN KEY(CategoryId) REFERENCES dbo.Categories(CategoryId)
-- 2. Order 
ALTER TABLE dbo.Orders ADD CONSTRAINT FK_Username FOREIGN KEY(Username) REFERENCES dbo.Accounts(Username)
-- 3. Order detail
ALTER TABLE dbo.OrderDetails ADD CONSTRAINT FK_ProductId FOREIGN KEY(ProductId) REFERENCES dbo.Products(ProductId)
ALTER TABLE dbo.OrderDetails ADD CONSTRAINT FK_OrderId FOREIGN KEY(OrderId) REFERENCES dbo.Orders(Id)
-- 4. Size
ALTER TABLE dbo.Size ADD CONSTRAINT FK_ProductId_TblSize FOREIGN KEY(ProductId) REFERENCES dbo.Products(ProductId)
-- 5. Favorites
ALTER TABLE dbo.Favorites ADD CONSTRAINT FK_ProductId_TblFav FOREIGN KEY(ProductId) REFERENCES dbo.Products(ProductId)
ALTER TABLE dbo.Favorites ADD CONSTRAINT FK_Username_TblFav FOREIGN KEY(Username) REFERENCES dbo.Accounts(Username)
-- 6. Shipping
ALTER TABLE dbo.Shipping ADD CONSTRAINT FK_Username_TblShipping FOREIGN KEY(ShipperId) REFERENCES dbo.Accounts(Username)
ALTER TABLE dbo.Shipping ADD CONSTRAINT FK_OrderId_TblShipping FOREIGN KEY(OrderId) REFERENCES dbo.Orders(Id)
-- 7. Visitor
ALTER TABLE dbo.Visitor ADD CONSTRAINT FK_Username_TblVisitor FOREIGN KEY(Username) REFERENCES dbo.Accounts(Username)
-- 8. PlaceReceipt
ALTER TABLE dbo.PlaceReceipt ADD CONSTRAINT FK_OrderId_TblPlaceReceipt FOREIGN KEY(OrderId) REFERENCES dbo.Orders(Id)


--DROP TABLE dbo.Categories
--DROP TABLE dbo.Products
--DROP TABLE dbo.Accounts
--DROP TABLE dbo.Orders
--DROP TABLE dbo.OrderDetails
SELECT * FROM dbo.Favorites
SELECT * FROM dbo.Categories
SELECT * FROM dbo.Products
SELECT * FROM dbo.OrderDetails
SELECT * FROM dbo.Orders
SELECT * FROM dbo.Accounts

DROP TABLE Favorites

-- Tạo một bản hóa đơn tạm với mục đích khi người dùng mua sẽ lưu hóa đơn với bảng này,
-- Nếu admin duyệt sẽ được lưu vào hòa đơn chính
SELECT * FROM dbo.Products AS P WHERE P.ProductId IN (
	SELECT TOP 10 OD.ProductId AS SL FROM dbo.OrderDetails AS OD
	GROUP BY OD.ProductId 
	ORDER BY COUNT(OD.ProductId) DESC
)
UPDATE dbo.Accounts SET Active = 'TRUE'

DELETE FROM dbo.Orders WHERE Username = 'admin'
DELETE dbo.Categories WHERE CategoryId = 'CI016'
UPDATE dbo.Orders SET Status = 'FALSE' WHERE Id = 7 OR Id = 8  OR Id = 9  OR Id = 10
SELECT * FROM dbo.PlaceReceipt
SELECT * FROM dbo.OrderDetails
SELECT * FROM dbo.Orders
DELETE FROM dbo.PlaceReceipt WHERE Id = 3
